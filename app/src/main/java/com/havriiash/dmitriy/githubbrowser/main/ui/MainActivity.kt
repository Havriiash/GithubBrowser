package com.havriiash.dmitriy.githubbrowser.main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.databinding.ActivityMainBinding
import com.havriiash.dmitriy.githubbrowser.databinding.NavHeaderMainBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowersFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowingFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.NewsFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoListFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    protected lateinit var preferences: GithubBrowserPreferences

    @Inject
    protected lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var headerBinding: NavHeaderMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        headerBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.nav_header_main, null, false)

        binding.activityMainNavigation.addHeaderView(headerBinding.root)
        activity_main_navigation.setNavigationItemSelectedListener(this)

        setSupportActionBar(binding.activityMainToolbar)

        viewModel.userObserver.observe(this, userObserver)
        viewModel.getUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.userObserver.removeObserver(userObserver)
    }

    override fun onResume() {
        super.onResume()
        if (supportFragmentManager.fragments.isEmpty()) {
            navigate(NewsFragment(), false, true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            if (isMain()) {
                drawer_layout.openDrawer(GravityCompat.START)
            } else {
                onBackPressed()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_news -> {
                navigate(NewsFragment(), false, true)
            }
            R.id.action_repo -> {
                navigate(RepoListFragment.create(preferences.loggedUser?.login!!), false, true)
            }
            R.id.action_gists -> {

            }
            R.id.action_followers -> {
                navigate(FollowersFragment.create(preferences.loggedUser?.login!!), false, true)
            }
            R.id.action_following -> {
                navigate(FollowingFragment.create(preferences.loggedUser?.login!!), false, true)
            }
            R.id.action_search -> {
            }
            R.id.action_settings -> {
            }
            R.id.action_logout -> {
                preferences.clearPreferences()
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showProgress(progress: Boolean) {
        headerBinding.drawerHeaderProgress.visibility = if (progress) View.VISIBLE else View.GONE
    }

    private val userObserver: Observer<RemoteResource<User>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
                showProgress(true)
            }
            RemoteResource.State.SUCCESS -> {
                headerBinding.user = it.data
                showProgress(false)
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
                showProgress(false)
            }
        }
    }
}