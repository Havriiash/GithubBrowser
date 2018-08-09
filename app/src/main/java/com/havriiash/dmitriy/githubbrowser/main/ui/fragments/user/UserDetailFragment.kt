package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentUserDetailBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.OrganizationsAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseFragment
import javax.inject.Inject

class UserDetailFragment: BaseFragment<User>() {

    @Inject
    lateinit var containerFragment: UserDetailContainerFragment

    private lateinit var binding: FragmentUserDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        showProgress(true)
        return binding.root
    }


    override fun showProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        binding.fragmentUserDetailProgress.visibility = visibility
        binding.fragmentUserDetailContent.visibility = View.GONE
        binding.fragmentUserDetailErrorView.visibility = View.GONE
    }

    override fun showError(msg: String) {
        showProgress(false)
        binding.fragmentUserDetailErrorView.setErrorText(msg)
        binding.fragmentUserDetailErrorView.setOnRetryClickListener(View.OnClickListener { containerFragment.refreshInfo() })
        binding.fragmentUserDetailErrorView.visibility = View.VISIBLE
    }

    override fun showContent(data: User) {
        showProgress(false)
        binding.fragmentUserDetailErrorView.visibility = View.GONE
        binding.user = data
        binding.fragmentUserDetailContent.visibility = View.VISIBLE
    }

    fun showOrganizations(organizations: List<Organization>) {
        if (!organizations.isEmpty()) {
            binding.fragmentUserDetailContentOrganizationsRv.adapter = OrganizationsAdapter(organizations, null)
            binding.fragmentUserDetailContentOrganizations.visibility = View.VISIBLE
        } else {
            binding.fragmentUserDetailContentOrganizations.visibility = View.GONE
        }
    }
}