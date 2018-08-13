package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.global.Constants
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer

abstract class BaseListFragment<D, M : ModelLayer> : BaseFragment<D>() {

    protected abstract val layoutListViewBinding: LayoutRecyclerViewBinding

    protected abstract val dataSource: BaseListDataSource<D, M>

    protected abstract val pageSize: Int

    override fun onDestroyView() {
        super.onDestroyView()
        dataSource.dispose()
    }


    protected abstract fun getAdapter(): PagedListAdapter<D, out RecyclerView.ViewHolder>

    protected fun setupListView() {
        layoutListViewBinding.layoutRecyclerViewSwipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        layoutListViewBinding.layoutRecyclerViewSwipeRefresh.setOnRefreshListener { setupListView() }

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(pageSize)
                .setPrefetchDistance(pageSize / 2)
                .build()
        val pagedList: PagedList<D> = PagedList.Builder(dataSource, pagedListConfig)
                .setFetchExecutor(Constants.MainThreadExecutor()) // used main thread because model uses rx inside
                .setNotifyExecutor(Constants.MainThreadExecutor())
                .build()

        val adapter = getAdapter()
        adapter.submitList(pagedList)
        layoutListViewBinding.layoutRecyclerViewRv.adapter = adapter

        dataSource.sourceObservable.observe(this, dataStorageObserver)
        showList()
    }

    protected val dataStorageObserver: Observer<RemoteResource<List<D>>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
                showProgress(true)
            }
            RemoteResource.State.SUCCESS -> {
                showProgress(false)
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
            }
        }
    }

    protected fun showList() {
        showProgress(true)
        layoutListViewBinding.layoutRecyclerViewErrorView.visibility = View.GONE
        layoutListViewBinding.layoutRecyclerViewRv.visibility = View.VISIBLE
    }

    override fun showError(msg: String) {
        layoutListViewBinding.layoutRecyclerViewRv.visibility = View.GONE
        showProgress(false)

        layoutListViewBinding.layoutRecyclerViewErrorView.setErrorText(msg)
        layoutListViewBinding.layoutRecyclerViewErrorView.setOnRetryClickListener(View.OnClickListener { _ -> setupListView() })
        layoutListViewBinding.layoutRecyclerViewErrorView.visibility = View.VISIBLE
    }

    override fun showProgress(progress: Boolean) {
        layoutListViewBinding.layoutRecyclerViewSwipeRefresh.isRefreshing = progress
    }

    override fun showContent(data: D) { }
}