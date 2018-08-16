package com.havriiash.dmitriy.githubbrowser.main.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.databinding.ActivityWebBrowserBinding
import dagger.android.support.DaggerAppCompatActivity

class WebBrowserActivity : DaggerAppCompatActivity() {
    companion object {
        const val URL_PARAM = "WebBrowserActivity.Params.Url"

        fun go(context: Context, url: String) {
            val browserIntent = Intent(context, WebBrowserActivity::class.java)
            browserIntent.putExtra(URL_PARAM, url)
            context.startActivity(browserIntent)
        }
    }

    private lateinit var binding: ActivityWebBrowserBinding

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_browser)
        url = intent.getStringExtra(URL_PARAM)

        binding.activityWebBrowserSrl.setOnRefreshListener { setupUI() }
        setupUI()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupUI() {
        setSupportActionBar(binding.activityWebBrowserToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = url

        binding.activityWebBrowserSrl.setColorSchemeResources(R.color.colorPrimary)

        binding.activityWebBrowserWb.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100 && binding.activityWebBrowserPb.visibility == View.GONE) {
                    binding.activityWebBrowserPb.visibility = View.VISIBLE
                }

                binding.activityWebBrowserPb.progress = progress
                if (progress == 100) {
                    binding.activityWebBrowserPb.visibility = View.GONE
                    binding.activityWebBrowserSrl.isRefreshing = false
                }
            }
        }
        binding.activityWebBrowserWb.loadUrl(url)
    }
}