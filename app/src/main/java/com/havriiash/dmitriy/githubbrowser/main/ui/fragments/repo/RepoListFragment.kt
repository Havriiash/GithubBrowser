package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo

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
import com.havriiash.dmitriy.githubbrowser.data.source.RepoDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoModel
import com.havriiash.dmitriy.githubbrowser.main.ui.RepoDetailActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.ReposAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseListFragment
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.DefaultItemClickListener
import javax.inject.Inject

class RepoListFragment : BaseListFragment<IShortRepoInfo, RepoModel>() {

    companion object {
        const val USER_PARAM = "RepoListFragment.Params.User"

        fun create(userName: String): RepoListFragment {
            val fragment = RepoListFragment()
            val args = Bundle()
            args.putString(USER_PARAM, userName)
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    protected lateinit var repoSource: RepoDataSource

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

    override val dataSource: BaseListDataSource<IShortRepoInfo, RepoModel>
        get() = repoSource

    override val pageSize: Int
        get() = 10

    override fun getAdapter(): PagedListAdapter<IShortRepoInfo, out RecyclerView.ViewHolder> = ReposAdapter(DefaultItemClickListener {
//        containerActivity.navigate(RepoDetailContainerFragment.create(it.getRepoOwner().login, it.getRepoName()))
        RepoDetailActivity.startRepoDetailActivity(containerActivity.getContainerActivity(), it.getRepoOwner().login, it.getRepoName())
    })

    override fun getToolbarTitle(): CharSequence {
        return if (containerActivity.isMain()) {
            getString(R.string.my_repos_title)
        } else {
            getString(R.string.repos_placeholder, userName)
        }
    }
}