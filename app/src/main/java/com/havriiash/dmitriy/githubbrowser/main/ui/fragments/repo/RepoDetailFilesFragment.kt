package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentRepoDetailFilesBinding
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.di.modules.repo.RepoDetailFilesFragmentModule
import com.havriiash.dmitriy.githubbrowser.main.ui.WebBrowserActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.FilesExplorerAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.RepoFilesAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailFilesViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.RepoDetailFilesVMFactory
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.DefaultItemClickListener
import javax.inject.Inject
import javax.inject.Named

class RepoDetailFilesFragment : BaseFragment<List<Repo.File>>() {

    companion object {
        const val PATH_PARAM = "RepoDetailFilesFragment.Params.Path"

        fun create(path: String?): RepoDetailFilesFragment {
            val fragment = RepoDetailFilesFragment()
            val args = Bundle()
            if (path != null) {
                args.putString(PATH_PARAM, path)
            }
            return fragment
        }
    }

    @Inject
    protected lateinit var factory: RepoDetailFilesVMFactory

    protected lateinit var viewModel: RepoDetailFilesViewModel

    @Inject
    protected lateinit var userName: String

    @field:[Inject Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)]
    protected lateinit var repoName: String

    @field:[Inject Named(RepoDetailFilesFragmentModule.PATH_QUALIFIER_NAME)]
    protected lateinit var rootPath: String


    private var currentPath = ""

    private lateinit var pagerBinding: FragmentRepoDetailFilesBinding

    private lateinit var binding: LayoutRecyclerViewBinding

    private lateinit var filesExplorerAdapter: FilesExplorerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(RepoDetailFilesViewModel::class.java)
        pagerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_detail_files, container, false)
        binding = pagerBinding.fragmentRepoDetailFilesContent!!

        filesExplorerAdapter = FilesExplorerAdapter(DefaultItemClickListener {
            currentPath = it
            filesExplorerAdapter.removeItemsToPath(it)
            pagerBinding.fragmentRepoDetailFilesExplorer.smoothScrollToPosition(filesExplorerAdapter.itemCount)
            viewModel.getRepoFile(it)
        })
        binding.layoutRecyclerViewSwipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        binding.layoutRecyclerViewSwipeRefresh.setOnRefreshListener { viewModel.getRepoFile(currentPath) }
        pagerBinding.fragmentRepoDetailFilesExplorer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        pagerBinding.fragmentRepoDetailFilesExplorer.adapter = filesExplorerAdapter
        return pagerBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.repoFileObservable.observe(this, repoFilesObserver)

        if (viewModel.repoFileObservable.value == null) {
            refreshInfo()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.repoFileObservable.removeObserver(repoFilesObserver)
    }


    override fun setupToolbar() { /* container fragment takes this work */
    }

    override fun showProgress(progress: Boolean) {
        binding.layoutRecyclerViewSwipeRefresh.isRefreshing = progress
    }

    override fun showError(msg: String) {
        binding.layoutRecyclerViewRv.visibility = View.GONE
        binding.layoutRecyclerViewErrorView.setErrorText(msg)
        binding.layoutRecyclerViewErrorView.setOnRetryClickListener(View.OnClickListener { refreshInfo() })
        binding.layoutRecyclerViewErrorView.visibility = View.VISIBLE
    }

    override fun showContent(data: List<Repo.File>) {
        showProgress(false)
        binding.layoutRecyclerViewErrorView.visibility = View.GONE
        binding.layoutRecyclerViewRv.adapter = RepoFilesAdapter(data, DefaultItemClickListener {
            if (it.type == "dir") {
                filesExplorerAdapter.addPath(it.path)
                pagerBinding.fragmentRepoDetailFilesExplorer.smoothScrollToPosition(filesExplorerAdapter.itemCount)
                viewModel.getRepoFile(it.path)
                currentPath = it.path
            } else {
                WebBrowserActivity.go(activity!!, it.links.html)
            }
        })
        binding.layoutRecyclerViewRv.visibility = View.VISIBLE
    }

    private fun refreshInfo() {
        viewModel.getRepoFile(rootPath)
    }

    private val repoFilesObserver: Observer<RemoteResource<List<Repo.File>>> = Observer {
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