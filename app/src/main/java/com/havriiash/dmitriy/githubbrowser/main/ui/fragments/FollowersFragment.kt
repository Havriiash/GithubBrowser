package com.havriiash.dmitriy.githubbrowser.main.ui.fragments

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.data.source.FollowersDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentFollowersBinding
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowersModel
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.FollowersAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseListFragment
import javax.inject.Inject

class FollowersFragment : BaseListFragment<Follower, FollowersModel>() {

    @Inject
    protected lateinit var preferences: GithubBrowserPreferences

    @Inject
    protected lateinit var followersSource: FollowersDataSource

    private lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_followers, container, false)

        setupListView()
        return binding.root
    }

    override val layoutListViewBinding: LayoutRecyclerViewBinding
        get() = binding.fragmentFollowersRecyclerViewLayout!!

    override val dataSource: BaseListDataSource<Follower, FollowersModel>
        get() = followersSource

    override val pageSize: Int
        get() = 10

    override fun getAdapter(): PagedListAdapter<Follower, out RecyclerView.ViewHolder> = FollowersAdapter(null)

    fun getUserName(): String {
        return preferences.loggedUser?.login!!
    }

}