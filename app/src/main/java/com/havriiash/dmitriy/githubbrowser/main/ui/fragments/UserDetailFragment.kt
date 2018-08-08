package com.havriiash.dmitriy.githubbrowser.main.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentUserDetailBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.OrganizationsAdapter
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailVMFactory
import com.havriiash.dmitriy.githubbrowser.utils.yearMonthDayStr
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.DefaultItemClickListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserDetailFragment: DaggerFragment() {
    companion object {
        const val USER_PARAM = "UserDetailFragment.Params.User"

        fun newInstance(userName: String?): UserDetailFragment {
            val fragment = UserDetailFragment()
            val args = Bundle()
            args.putString(USER_PARAM, userName)
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    protected lateinit var preferences: GithubBrowserPreferences

    @Inject
    protected lateinit var factory: UserDetailVMFactory

    private lateinit var viewModel: UserDetailViewModel

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
            viewModel.getUserInfo()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.userObservable.removeObserver(userObserver)
        viewModel.organizationObservable.removeObserver(organizationsObserver)
    }

    fun getUserName(): String {
        return arguments?.getString(UserDetailFragment.USER_PARAM, "")!!
    }


    private val userObserver: Observer<RemoteResource<User>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> { showProgress(true) }
            RemoteResource.State.SUCCESS -> {
                showContent(it.data!!)
                if (viewModel.organizationObservable.value == null) {
                    viewModel.getOrganizations()
                }
            }
            RemoteResource.State.ERROR -> { showError(it.throwable?.message!!) }
        }
    }

    private val organizationsObserver: Observer<RemoteResource<List<Organization>>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> { }
            RemoteResource.State.SUCCESS -> { showContent(it.data!!) }
            RemoteResource.State.ERROR -> { showError(it.throwable?.message!!) }
        }
    }

    private fun showProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        binding.fragmentUserDetailProgressToolbar.visibility = visibility
        binding.fragmentUserDetailProgress.visibility = visibility
        binding.fragmentUserDetailErrorView.visibility = View.GONE
    }

    private fun showError(msg: String) {
        showProgress(false)
        binding.fragmentUserDetailErrorView.setErrorText(msg)
        binding.fragmentUserDetailErrorView.setOnRetryClickListener(View.OnClickListener { viewModel.getUserInfo() })
        binding.fragmentUserDetailErrorView.visibility = View.VISIBLE
    }

    private fun showContent(user: User) {
        showProgress(false)
        binding.fragmentUserDetailErrorView.visibility = View.GONE
        binding.user = user
        binding.fragmentUserDetailContent.visibility = View.VISIBLE
        binding.fragmentUserDetailToolbar.title = user.login
    }

    private fun showContent(organizations: List<Organization>) {
        if (!organizations.isEmpty()) {
            binding.fragmentUserDetailContentOrganizationsRv.adapter = OrganizationsAdapter(organizations, null)
            binding.fragmentUserDetailContentOrganizations.visibility = View.VISIBLE
        } else {
            binding.fragmentUserDetailContentOrganizations.visibility = View.GONE
        }
    }
}