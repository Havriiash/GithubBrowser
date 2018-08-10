package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.base.FragmentContainerListener
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailVMFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserDetailContainerFragment : BaseContainerFragment(), FragmentContainerListener<User> {

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


    override val fragments: List<DaggerFragment>
        get() = arrayListOf(UserDetailInfoFragment(), UserDetailActivityFragment(), UserDetailStarredFragment())

    override val titles: List<String>
        get() = arrayListOf(getString(R.string.tab_info_title), getString(R.string.tab_activity_title), getString(R.string.tab_starred_title))

    override val pageChangeListener: ViewPager.OnPageChangeListener
        get() = object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) { }
        }


    @Inject
    protected lateinit var factory: UserDetailVMFactory

    protected lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(UserDetailViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.userObservable.observe(this, userObserver)

        if (viewModel.userObservable.value == null) {
            viewModel.getUserInfo()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.userObservable.removeObserver(userObserver)
    }

    override fun onDataLoaded(data: User) {
        containerBinding.user = data
        containerBinding.fragmentUserDetailToolbar.title = data.login
        containerBinding.fragmentUserDetailProgressToolbar.visibility = View.GONE
    }

    override fun onError(msg: String) {
        onProgress(true)
    }

    override fun onProgress(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        containerBinding.fragmentUserDetailProgressToolbar.visibility = visibility
    }

    private val userObserver: Observer<RemoteResource<User>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
                onProgress(true)
            }
            RemoteResource.State.SUCCESS -> {
                onDataLoaded(it.data!!)
                if (viewModel.organizationObservable.value == null) {
                    viewModel.getOrganizations()
                }
            }
            RemoteResource.State.ERROR -> {
                onError(it.throwable?.message!!)
            }
        }
    }
}