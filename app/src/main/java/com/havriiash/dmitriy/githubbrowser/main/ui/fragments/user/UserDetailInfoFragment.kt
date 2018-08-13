package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentUserDetailBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.OrganizationsAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.base.FragmentContainerListener
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowersFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowingFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoListFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailVMFactory
import com.havriiash.dmitriy.githubbrowser.utils.setViewEnabled
import javax.inject.Inject

class UserDetailInfoFragment : BaseFragment<User>() {

    @Inject
    protected lateinit var factory: UserDetailVMFactory

    protected lateinit var viewModel: UserDetailViewModel

    @Inject
    protected lateinit var containerFragment: FragmentContainerListener<User>

    @Inject
    protected lateinit var userName: String

    private lateinit var binding: FragmentUserDetailBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(UserDetailViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.userObservable.observe(this, userObserver)
        viewModel.organizationObservable.observe(this, organizationsObserver)

        if (viewModel.userObservable.value == null) {
            refreshInfo()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.userObservable.removeObserver(userObserver)
        viewModel.organizationObservable.removeObserver(organizationsObserver)
    }


    override fun showProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        containerFragment.onProgress(progress)
        binding.fragmentUserDetailProgress.visibility = visibility
        binding.fragmentUserDetailContent.visibility = View.GONE
        binding.fragmentUserDetailErrorView.visibility = View.GONE
    }

    override fun showError(msg: String) {
        showProgress(false)
        containerFragment.onError(msg)
        binding.fragmentUserDetailErrorView.setErrorText(msg)
        binding.fragmentUserDetailErrorView.setOnRetryClickListener(View.OnClickListener { refreshInfo() })
        binding.fragmentUserDetailErrorView.visibility = View.VISIBLE
    }

    override fun showContent(data: User) {
        showProgress(false)
        containerFragment.onDataLoaded(data)
        binding.fragmentUserDetailErrorView.visibility = View.GONE
        binding.user = data
        binding.fragmentUserDetailContent.visibility = View.VISIBLE
        setListeners(data)
    }

    override fun setupToolbar() { /* container fragment takes this work */ }

    private fun showOrganizations(organizations: List<Organization>) {
        if (!organizations.isEmpty()) {
            binding.fragmentUserDetailContentOrganizationsRv.adapter = OrganizationsAdapter(organizations, null)
            binding.fragmentUserDetailContentOrganizations.visibility = View.VISIBLE
        } else {
            binding.fragmentUserDetailContentOrganizations.visibility = View.GONE
        }
    }

    private fun refreshInfo() {
        viewModel.getUserInfo()
    }

    private fun setListeners(data: User) {
        if (data.followers > 0) {
            binding.fragmentUserDetailFollowers.setOnClickListener {
                containerActivity.navigate(FollowersFragment.create(userName))
            }
        } else {
            binding.fragmentUserDetailFollowers.setViewEnabled(false)
        }
        if (data.following > 0) {
            binding.fragmentUserDetailFollowing.setOnClickListener {
                containerActivity.navigate(FollowingFragment.create(userName))
            }
        } else {
            binding.fragmentUserDetailFollowing.setViewEnabled(false)
        }
        if (data.publicRepos > 0) {
            binding.fragmentUserDetailRepos.setOnClickListener {
                containerActivity.navigate(RepoListFragment.create(userName))
            }
        } else {
            binding.fragmentUserDetailRepos.setViewEnabled(false)
        }
    }

    private val userObserver: Observer<RemoteResource<User>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
                showProgress(true)
            }
            RemoteResource.State.SUCCESS -> {
                showContent(it.data!!)
                if (viewModel.organizationObservable.value == null) {
                    viewModel.getOrganizations()
                }
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
            }
        }
    }

    private val organizationsObserver: Observer<RemoteResource<List<Organization>>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
            }
            RemoteResource.State.SUCCESS -> {
                showOrganizations(it.data!!)
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
            }
        }
    }

}