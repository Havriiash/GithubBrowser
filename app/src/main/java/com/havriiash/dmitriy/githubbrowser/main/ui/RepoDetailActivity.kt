package com.havriiash.dmitriy.githubbrowser.main.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutContainerRepoBinding
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseContainerActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailCommitsFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailFilesFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailInfoFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.RepoDetailVMFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class RepoDetailActivity: BaseContainerActivity<Repo>() {

    companion object {
        private const val CURRENT_PAGE_PARAM = "RepoDetailActivity.Params.CurrentPage"

        const val USER_PARAM = "RepoDetailActivity.Params.User"
        const val REPO_PARAM = "RepoDetailActivity.Params.Repo"

        fun startRepoDetailActivity(context: Context, userName: String, repoName: String) {
            val intent = Intent(context, RepoDetailActivity::class.java)
            intent.putExtra(USER_PARAM, userName)
            intent.putExtra(REPO_PARAM, repoName)
            context.startActivity(intent)
        }
    }


    @Inject
    protected lateinit var userName: String

    @field:[Inject Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)]
    protected lateinit var repoName: String

    @Inject
    protected lateinit var factory: RepoDetailVMFactory

    protected var currentPage: Int = 0

    private lateinit var viewModel: RepoDetailViewModel

    private lateinit var layoutContainerRepoBinding: LayoutContainerRepoBinding


    override val fragments: List<DaggerFragment>
        get() = arrayListOf(RepoDetailInfoFragment(), RepoDetailFilesFragment.create(null), RepoDetailCommitsFragment())

    override val titles: List<String>
        get() = arrayListOf(getString(R.string.tab_repo_info_title), getString(R.string.tab_repo_files_title), getString(R.string.tab_repo_commits_title))

    override val pageChangeListener: ViewPager.OnPageChangeListener
        get() = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(RepoDetailViewModel::class.java)
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt(CURRENT_PAGE_PARAM, 0)
        }
        layoutContainerRepoBinding = inflateViewIntoToolbar(R.layout.layout_container_repo)
        supportActionBar?.title = repoName
    }

    override fun onResume() {
        super.onResume()
        pageChangeListener.onPageSelected(currentPage)
        viewModel.userRepoInfo.observe(this, repoObserver)

        if (viewModel.userRepoInfo.value == null) {
            refreshInfo()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.userRepoInfo.removeObserver(repoObserver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_PAGE_PARAM, currentPage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_repo_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_repo_detail_star -> { showError(getString(R.string.developing_progress)) }
            R.id.menu_repo_detail_branch -> { showError(getString(R.string.developing_progress)) }
        }
        return super.onOptionsItemSelected(item)
    }


    private val repoObserver: Observer<RemoteResource<Repo>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> { showProgress(true) }
            RemoteResource.State.SUCCESS -> {
                showProgress(false)
                containerBinding.backgroundImageUrl = it.data!!.owner.avatarUrl
                layoutContainerRepoBinding.repo = it.data
            }
            RemoteResource.State.ERROR -> { showError(it.throwable?.message.let { _ -> "something went wrong" }) }
        }
    }


    override fun refreshInfo() {
        viewModel.getUserRepo()
    }

    override fun getDataObservable(): LiveData<RemoteResource<Repo>> {
        return viewModel.userRepoInfo
    }

    override fun showProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        containerBinding.activityContainerToolbarProgress.visibility = visibility
    }
}