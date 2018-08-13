package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.data.source.UserActivityDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailActivityModel
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.UserActivityAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseListFragment
import javax.inject.Inject

class UserDetailActivityFragment: BaseListFragment<User.UserActivity, UserDetailActivityModel>() {

    @Inject
    protected lateinit var userActivityDataSource: UserActivityDataSource

    private lateinit var binding: LayoutRecyclerViewBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.layout_recycler_view, container, false)

        setupListView()
        return binding.root
    }


    override val layoutListViewBinding: LayoutRecyclerViewBinding
        get() = binding

    override val dataSource: BaseListDataSource<User.UserActivity, UserDetailActivityModel>
        get() = userActivityDataSource

    override val pageSize: Int
        get() = 10

    override fun getAdapter(): PagedListAdapter<User.UserActivity, out RecyclerView.ViewHolder> = UserActivityAdapter(null)

    override fun setupToolbar() { /* container fragment takes this work */ }

}