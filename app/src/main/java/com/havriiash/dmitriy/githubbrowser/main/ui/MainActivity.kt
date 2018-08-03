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
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.databinding.ActivityMainBinding
import com.havriiash.dmitriy.githubbrowser.databinding.NavHeaderMainBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseActivity
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

        viewModel.userObserver.observe(this, userObserver)
        viewModel.getUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.userObserver.removeObserver(userObserver)
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_container, NewsFragment())
                .commit()
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
            }
            R.id.action_repo -> {
            }
            R.id.action_gists -> {
            }
            R.id.action_followers -> {
            }
            R.id.action_following -> {
            }
            R.id.action_search -> {
            }
            R.id.action_settings -> {
            }
            R.id.action_logout -> logout()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showProgress(progress: Boolean) {

    }

    private fun logout() {
        preferences.clearPreferences()
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }

    private val userObserver: Observer<RemoteResource<User>> = Observer {
        when(it?.state) {
            RemoteResource.State.LOADING -> {}
            RemoteResource.State.SUCCESS -> {
                showProgress(false)
                headerBinding.user = it.data
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
                showProgress(false)
            }
        }
    }
}