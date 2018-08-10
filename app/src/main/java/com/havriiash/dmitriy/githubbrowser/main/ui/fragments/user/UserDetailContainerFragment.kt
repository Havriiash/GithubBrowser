package com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.base.FragmentContainerListener
import dagger.android.support.DaggerFragment

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
        get() = arrayListOf(UserDetailFragment(), UserDetailStarredFragment())

    override val titles: List<String>
        get() = arrayListOf("Info", "Starred")

    override val pageChangeListener: ViewPager.OnPageChangeListener
        get() = object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val prevTitle = containerBinding.fragmentUserDetailToolbar.title
                containerBinding.fragmentUserDetailToolbar.title = "$prevTitle / ${adapter.getPageTitle(position)}"
            }
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

}