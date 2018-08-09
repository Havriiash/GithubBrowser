package com.havriiash.dmitriy.githubbrowser.main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.databinding.ActivitySplashBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseActivity
import com.havriiash.dmitriy.githubbrowser.main.vm.SplashViewModel
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
        binding.activitySplashBtnSignIn.setOnClickListener {
            it.isEnabled = false
            showProgress(true)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(GithubApi.AUTHORIZE_URL)))
        }

        viewModel.authorizeObserver.observe(this, authObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.authorizeObserver.removeObserver(authObserver)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isAuthrorized()) {
            startBrowser()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            val code = viewModel.parseIntent(intent)
            if (code != null) {
                viewModel.authorize(code)
            }
        }
    }

    override fun showProgress(progress: Boolean) {
        binding.activitySplashProgress.visibility = if (progress) View.VISIBLE else View.GONE
    }

    private fun startBrowser() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private val authObserver: Observer<RemoteResource<String>> = Observer {
        when (it?.state) {
            RemoteResource.State.LOADING -> {
                showProgress(true)
            }
            RemoteResource.State.SUCCESS -> {
                startBrowser()
                showProgress(false)
                binding.activitySplashBtnSignIn.isEnabled = true
            }
            RemoteResource.State.ERROR -> {
                showError(it.throwable?.message!!)
                showProgress(false)
                binding.activitySplashBtnSignIn.isEnabled = true
            }
        }
    }
}