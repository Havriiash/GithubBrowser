package com.havriiash.dmitriy.githubbrowser.main.ui.base

import com.havriiash.dmitriy.githubbrowser.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<D>: DaggerFragment() {

    @Inject
    protected lateinit var containerActivity: ContainerActivity


    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    abstract fun showProgress(progress: Boolean)

    abstract fun showError(msg: String)

    abstract fun showContent(data: D)

    open fun getToolbarTitle(): CharSequence = this::class.java.name

    open fun setupToolbar() {
        containerActivity.getContainerSupportActionBar()?.title = getToolbarTitle()
        if (containerActivity.isMain()) {
            containerActivity.getContainerSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_menu)
        } else {
            containerActivity.getContainerSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        containerActivity.getContainerSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

}