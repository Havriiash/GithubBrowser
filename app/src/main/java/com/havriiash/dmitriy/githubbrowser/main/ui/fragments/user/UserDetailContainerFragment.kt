package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailContainerViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailContainerVMFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserDetailContainerFragment: BaseContainerFragment() {
    companion object {
        const val USER_PARAM = "UserDetailContainerFragment.Params.User"

        fun newInstance(userName: String): UserDetailContainerFragment {
            val fragment = UserDetailContainerFragment()
            val args = Bundle()
            args.putString(USER_PARAM, userName)
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    protected lateinit var factory: UserDetailContainerVMFactory

    protected lateinit var viewModel: UserDetailContainerViewModel

    private val userDetailFragment = UserDetailFragment()


    override val fragments: List<DaggerFragment>
        get() = arrayListOf(userDetailFragment, UserDetailStarredFragment())

    override val titles: List<String>
        get() = arrayListOf("Info", "Starred")

    override val pageChangeListener: ViewPager.OnPageChangeListener
        get() = object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                val prevTitle = containerBinding.fragmentUserDetailToolbar.title
                containerBinding.fragmentUserDetailToolbar.title = "$prevTitle / ${adapter.getPageTitle(position)}"
            }
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(UserDetailContainerViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
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


    fun refreshInfo() {
        viewModel.getUserInfo()
    }

    private fun showProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        containerBinding.fragmentUserDetailProgressToolbar.visibility = visibility
    }

    private fun showContent(data: User) {
        showProgress(false)
        containerBinding.user = data
        containerBinding.fragmentUserDetailToolbar.title = data.login
        userDetailFragment.showContent(data)
    }

    private fun showError(msg: String) {
        showProgress(false)
        userDetailFragment.showError(msg)
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
            RemoteResource.State.SUCCESS -> { userDetailFragment.showOrganizations(it.data!!) }
            RemoteResource.State.ERROR -> { showError(it.throwable?.message!!) }
        }
    }
}