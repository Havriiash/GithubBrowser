package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.IShortRepoInfo
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.data.source.StarredDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailStarredModel
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.ReposAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseListFragment
import javax.inject.Inject

class UserDetailStarredFragment: BaseListFragment<IShortRepoInfo, UserDetailStarredModel>() {

    @Inject
    protected lateinit var starredDataSource: StarredDataSource

    private lateinit var binding: LayoutRecyclerViewBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.layout_recycler_view, container, false)

        setupListView()
        return binding.root
    }


    override val layoutListViewBinding: LayoutRecyclerViewBinding
        get() = binding

    override val dataSource: BaseListDataSource<IShortRepoInfo, UserDetailStarredModel>
        get() = starredDataSource

    override val pageSize: Int
        get() = 10

    override fun getAdapter(): PagedListAdapter<IShortRepoInfo, out RecyclerView.ViewHolder> = ReposAdapter(null)

    override fun setupToolbar() { /* container fragment takes this work */ }

}

