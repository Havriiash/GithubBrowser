package com.havriiash.dmitriy.githubbrowser.main.ui.fragments

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.data.source.FollowersDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowersModel
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.FollowersAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseListFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailContainerFragment
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.DefaultItemClickListener
import javax.inject.Inject

class FollowersFragment : BaseListFragment<Follower, FollowersModel>() {

    companion object {
        const val USER_PARAM = "FollowersFragment.Params.User"

        fun create(userName: String): FollowersFragment {
            val fragment = FollowersFragment()
            val args = Bundle()
            args.putString(USER_PARAM, userName)
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    protected lateinit var followersSource: FollowersDataSource

    @Inject
    protected lateinit var userName: String

    private lateinit var binding: LayoutRecyclerViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_recycler_view, container, false)
        setupListView()
        return binding.root
    }

    override val layoutListViewBinding: LayoutRecyclerViewBinding
        get() = binding

    override val dataSource: BaseListDataSource<Follower, FollowersModel>
        get() = followersSource

    override val pageSize: Int
        get() = 10

    override fun getAdapter(): PagedListAdapter<Follower, out RecyclerView.ViewHolder> = FollowersAdapter(DefaultItemClickListener {
        containerActivity.navigate(UserDetailContainerFragment.newInstance(it.login))
    })

    override fun getToolbarTitle(): CharSequence {
        return if (containerActivity.isMain()) {
            "My followers"
        } else {
            "Followers / $userName"
        }
    }
}