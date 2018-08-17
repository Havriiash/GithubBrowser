package com.havriiash.dmitriy.githubbrowser.main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.databinding.ActivityCommitDetailBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.CommitFilesAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseActivity
import com.havriiash.dmitriy.githubbrowser.main.vm.CommitDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.CommitDetailVMFactory
import javax.inject.Inject

class CommitDetailActivity: BaseActivity() {

    companion object {
        const val USER_NAME_PARAM = "CommitDetailActivity.Params.UserName"
        const val REPO_NAME_PARAM = "CommitDetailActivity.Params.RepoName"
        const val SHA_ID_PARAM = "CommitDetailActivity.Params.ShaId"

        fun showCommitDetail(context: Context, userName: String, repoName: String, shaId: String) {
            val intent = Intent(context, CommitDetailActivity::class.java)
            intent.putExtra(USER_NAME_PARAM, userName)
            intent.putExtra(REPO_NAME_PARAM, repoName)
            intent.putExtra(SHA_ID_PARAM, shaId)
            context.startActivity(intent)
        }
    }


    @Inject
    protected lateinit var factory: CommitDetailVMFactory

    private lateinit var binding: ActivityCommitDetailBinding

    private lateinit var viewModel: CommitDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commit_detail)
        setupUI()

        viewModel = ViewModelProviders.of(this, factory).get(CommitDetailViewModel::class.java)
        viewModel.commitObservable.observe(this, commitObserver)
        if (viewModel.commitObservable.value == null) {
            refreshInfo()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.commitObservable.removeObserver(commitObserver)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun showProgress(progress: Boolean) {
        binding.activityCommitDetailProgress.visibility = if (progress) View.VISIBLE else View.GONE
        binding.activityCommitDetailRecyclerView?.layoutRecyclerViewRv?.visibility = if (progress) View.GONE else View.VISIBLE
        binding.activityCommitDetailRecyclerView?.layoutRecyclerViewSwipeRefresh?.isRefreshing = progress
    }

    private fun showData(commit: Commit) {
        showProgress(false)
        binding.commitEntity = commit
        supportActionBar?.title = "Commit ${commit.sha.substring(0, 7)}"
        binding.activityCommitDetailRecyclerView?.layoutRecyclerViewRv?.adapter = CommitFilesAdapter(commit.files, null)
    }

    private fun setupUI() {
        binding.activityCommitDetailRecyclerView?.layoutRecyclerViewSwipeRefresh?.setColorSchemeResources(R.color.colorPrimary)
        binding.activityCommitDetailRecyclerView?.layoutRecyclerViewSwipeRefresh?.setOnRefreshListener { refreshInfo() }

        setSupportActionBar(binding.activityCommitDetailToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }

    private fun refreshInfo() {
        viewModel.getCommitInfo()
    }


    private val commitObserver: Observer<RemoteResource<Commit>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> { showProgress(true) }
            RemoteResource.State.SUCCESS -> { showData(it.data!!) }
            RemoteResource.State.ERROR -> {
                showProgress(false)
                binding.activityCommitDetailRecyclerView?.layoutRecyclerViewRv?.visibility = View.GONE
                binding.activityCommitDetailRecyclerView?.layoutRecyclerViewErrorView?.visibility = View.VISIBLE
                binding.activityCommitDetailRecyclerView?.layoutRecyclerViewErrorView?.setErrorText(it.throwable?.message!!)
                binding.activityCommitDetailRecyclerView?.layoutRecyclerViewErrorView?.setOnRetryClickListener(View.OnClickListener { _ -> refreshInfo() })
            }
        }
    }
}