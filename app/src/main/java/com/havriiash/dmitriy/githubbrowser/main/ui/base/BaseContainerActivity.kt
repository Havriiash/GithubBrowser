package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.MenuItem
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.databinding.ActivityContainerBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.PagerFragmentAdapter
import dagger.android.support.DaggerFragment

abstract class BaseContainerActivity<D>: BaseActivity(), IActivityContainer<D> {

    abstract val fragments: List<DaggerFragment>

    abstract val titles: List<String>

    protected abstract val pageChangeListener: ViewPager.OnPageChangeListener


    protected lateinit var adapter: PagerFragmentAdapter

    protected lateinit var containerBinding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        containerBinding = DataBindingUtil.setContentView(this, R.layout.activity_container)
        setupPager()
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    protected fun <DB: ViewDataBinding> inflateViewIntoToolbar(@LayoutRes layoutId: Int ): DB {
        val inflater = LayoutInflater.from(containerBinding.root.context)
        val container = containerBinding.activityContainerToolbarContainer
        val viewBinding = DataBindingUtil.inflate<DB>(inflater, layoutId, container, false)
        containerBinding.activityContainerToolbarContainer.addView(viewBinding.root)
        return viewBinding
    }

    private fun setupPager() {
        adapter = PagerFragmentAdapter(supportFragmentManager, fragments, titles)
        containerBinding.activityContainerViewPager.adapter = adapter
        containerBinding.activityContainerViewPager.addOnPageChangeListener(pageChangeListener)
        containerBinding.activityContainerTabLayout.setupWithViewPager(containerBinding.activityContainerViewPager)
    }

    private fun setupToolbar() {
        setSupportActionBar(containerBinding.activityContainerToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}