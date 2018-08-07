package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.global.Constants
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import dagger.android.support.DaggerFragment

abstract class BaseListFragment<D, M : ModelLayer> : DaggerFragment() {

    protected abstract val layoutListViewBinding: LayoutRecyclerViewBinding

    protected abstract val dataSource: BaseListDataSource<D, M>

    protected abstract val pageSize: Int

    override fun onDestroyView() {
        super.onDestroyView()
        dataSource.dispose()
    }


    protected abstract fun getAdapter(): PagedListAdapter<D, out RecyclerView.ViewHolder>

    protected fun setupListView() {
        layoutListViewBinding.layoutRecyclerViewSwipeRefresh.setOnRefreshListener { setupListView() }

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(pageSize)
                .build()
        val pagedList: PagedList<D> = PagedList.Builder(dataSource, pagedListConfig)
                .setFetchExecutor(Constants.MainThreadExecutor()) // TODO: need to check executors because model uses rx inside
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
                layoutListViewBinding.layoutRecyclerViewSwipeRefresh.isRefreshing = true
            }
            RemoteResource.State.SUCCESS -> {
                layoutListViewBinding.layoutRecyclerViewSwipeRefresh.isRefreshing = false
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
            }
        }
    }

    protected fun showList() {
        layoutListViewBinding.layoutRecyclerViewSwipeRefresh.isRefreshing = true
        layoutListViewBinding.layoutRecyclerViewErrorView.visibility = View.GONE
        layoutListViewBinding.layoutRecyclerViewRv.visibility = View.VISIBLE
    }

    protected fun showError(msg: String) {
        layoutListViewBinding.layoutRecyclerViewRv.visibility = View.GONE
        layoutListViewBinding.layoutRecyclerViewSwipeRefresh.isRefreshing = false

        layoutListViewBinding.layoutRecyclerViewErrorView.setErrorText(msg)
        layoutListViewBinding.layoutRecyclerViewErrorView.setOnRetryClickListener(View.OnClickListener { _ -> setupListView() })
        layoutListViewBinding.layoutRecyclerViewErrorView.visibility = View.VISIBLE
    }
}