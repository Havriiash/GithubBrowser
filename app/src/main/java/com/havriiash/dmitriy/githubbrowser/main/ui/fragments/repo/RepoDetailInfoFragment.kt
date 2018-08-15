package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentRepoDetailBinding
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.base.IActivityContainer
import com.havriiash.dmitriy.githubbrowser.utils.setViewEnabled
import javax.inject.Inject
import javax.inject.Named

class RepoDetailInfoFragment : BaseFragment<Repo>() {

    @Inject
    protected lateinit var containerListener: IActivityContainer<Repo>

    @Inject
    protected lateinit var userName: String

    @field:[Inject Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)]
    protected lateinit var repoName: String

    private lateinit var binding: FragmentRepoDetailBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_detail, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        containerListener.getDataObservable().observe(this, repoObserver)
    }

    override fun onPause() {
        super.onPause()
        containerListener.getDataObservable().removeObserver(repoObserver)
    }


    override fun setupToolbar() { /* container fragment takes this work */ }

    override fun showProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        binding.fragmentRepoDetailProgress.visibility = visibility
        binding.fragmentRepoDetailContent.visibility = View.GONE
        binding.fragmentRepoDetailErrorView.visibility = View.GONE
    }

    override fun showError(msg: String) {
        showProgress(false)
        binding.fragmentRepoDetailErrorView.setErrorText(msg)
        binding.fragmentRepoDetailErrorView.setOnRetryClickListener(View.OnClickListener { containerListener.refreshInfo() })
        binding.fragmentRepoDetailErrorView.visibility = View.VISIBLE
    }

    override fun showContent(data: Repo) {
        showProgress(false)
        binding.fragmentRepoDetailErrorView.visibility = View.GONE
        binding.repo = data
        binding.fragmentRepoDetailContent.visibility = View.VISIBLE
        setListeners(data)
    }

    private fun setListeners(data: Repo) {
        if (data.open_issues > 0) {

        } else {
            binding.fragmentRepoDetailIssues.setViewEnabled(false)
        }
        if (data.stargazers_count > 0) {

        } else {
            binding.fragmentRepoDetailStargazers.setViewEnabled(false)
        }
        if (data.forks > 0) {

        } else {
            binding.fragmentRepoDetailForks.setViewEnabled(false)
        }
        if (data.watchers_count > 0) {

        } else {
            binding.fragmentRepoDetailWatchers.setViewEnabled(false)
        }
    }


    private val repoObserver: Observer<RemoteResource<Repo>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
                showProgress(true)
            }
            RemoteResource.State.SUCCESS -> {
                showContent(it.data!!)
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
            }
        }
    }
}