package com.havriiash.dmitriy.githubbrowser.main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentNewsBinding
import com.havriiash.dmitriy.githubbrowser.global.Constants
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.NewsAdapter
import com.havriiash.dmitriy.githubbrowser.data.list.NewsDataStorage
import com.havriiash.dmitriy.githubbrowser.main.vm.NewsViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.NewsVMProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsFragment : DaggerFragment() {

    @Inject
    protected lateinit var factory: NewsVMProviderFactory

    @Inject
    protected lateinit var dataSource: NewsDataStorage

    private lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(NewsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewSwipeRefresh?.setOnRefreshListener { setupRecyclerView() }
        setupRecyclerView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataSource.dispose()
    }

    private fun setupRecyclerView() {
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(10)
                .build()
        val pagedList: PagedList<News> = PagedList.Builder(dataSource, pagedListConfig)
                .setFetchExecutor(Constants.MainThreadExecutor()) // TODO: need to check executors because model uses rx inside
                .setNotifyExecutor(Constants.MainThreadExecutor())
                .build()

        val adapter = NewsAdapter()
        adapter.submitList(pagedList)
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewRv?.adapter = adapter

        dataSource.storageObservable.observe(this, dataStorageObserver)
        showList()
    }

    private val dataStorageObserver: Observer<RemoteResource<List<News>>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> { binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewSwipeRefresh?.isRefreshing = true }
            RemoteResource.State.SUCCESS -> { binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewSwipeRefresh?.isRefreshing = false }
            RemoteResource.State.ERROR -> { showError(it.throwable?.message!!) }
        }
    }

    private fun showList() {
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewSwipeRefresh?.isRefreshing = true
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewErrorView?.visibility = View.GONE
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewRv?.visibility = View.VISIBLE
    }

    private fun showError(msg: String) {
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewRv?.visibility = View.GONE
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewSwipeRefresh?.isRefreshing = false

        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewErrorView?.setErrorText(msg)
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewErrorView?.setOnRetryClickListener(View.OnClickListener { _-> setupRecyclerView() })
        binding.fragmentNewsLayoutRecyclerView?.layoutRecyclerViewErrorView?.visibility = View.VISIBLE
    }
}
