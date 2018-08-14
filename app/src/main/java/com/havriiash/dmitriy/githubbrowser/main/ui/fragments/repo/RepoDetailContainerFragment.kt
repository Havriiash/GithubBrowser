package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutContainerRepoBinding
import com.havriiash.dmitriy.githubbrowser.di.modules.repo.RepoDetailContainerFragmentModule
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.RepoDetailVMFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class RepoDetailContainerFragment: BaseContainerFragment<Repo>() {

    companion object {
        private const val CURRENT_PAGE_PARAM = "RepoDetailContainerFragment.Params.CurrentPage"

        const val USER_PARAM = "RepoDetailContainerFragment.Params.User"
        const val REPO_PARAM = "RepoDetailContainerFragment.Params.Repo"

        fun create(userName: String, repoName: String): RepoDetailContainerFragment {
            val fragment = RepoDetailContainerFragment()
            val args = Bundle()
            args.putString(USER_PARAM, userName)
            args.putString(REPO_PARAM, repoName)
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    protected lateinit var userName: String

    @field:[Inject Named(RepoDetailContainerFragmentModule.REPO_QUALIFIER_NAME)]
    protected lateinit var repoName: String

    @Inject
    protected lateinit var factory: RepoDetailVMFactory

    protected var currentPage: Int = 0

    private lateinit var viewModel: RepoDetailViewModel

    private lateinit var layoutContainerRepoBinding: LayoutContainerRepoBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(RepoDetailViewModel::class.java)
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt(CURRENT_PAGE_PARAM, 0)
        }
        layoutContainerRepoBinding = DataBindingUtil.inflate(inflater, R.layout.layout_container_repo, container, false)
        containerBinding.fragmentContainerToolbarContainer.addView(layoutContainerRepoBinding.root)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        pageChangeListener.onPageSelected(currentPage)
        viewModel.userRepoInfo.observe(this, repoObserver)

        if (viewModel.userRepoInfo.value == null) {
            viewModel.getUserRepo()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.userRepoInfo.removeObserver(repoObserver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RepoDetailContainerFragment.CURRENT_PAGE_PARAM, currentPage)
    }


    override val fragments: List<DaggerFragment>
        get() = arrayListOf(RepoDetailInfoFragment())

    override val titles: List<String>
        get() = arrayListOf("Info")

    override val pageChangeListener: ViewPager.OnPageChangeListener
        get() = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                currentPage = position
                containerActivity.getContainerSupportActionBar()?.title = "$repoName / ${titles[position]}"
            }
        }


    override fun onDataLoaded(data: Repo) {
        containerBinding.backgroundImageUrl = data.owner.avatarUrl
        layoutContainerRepoBinding.repo = data
        containerBinding.fragmentContainerToolbarProgress.visibility = View.GONE
    }

    override fun onProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        containerBinding.fragmentContainerToolbarProgress.visibility = visibility
    }

    override fun onError(msg: String) {
        onProgress(true)
    }


    private val repoObserver: Observer<RemoteResource<Repo>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> { onProgress(true) }
            RemoteResource.State.SUCCESS -> { onDataLoaded(it.data!!) }
            RemoteResource.State.ERROR -> { onError(it.throwable?.message!!) }
        }
    }

}