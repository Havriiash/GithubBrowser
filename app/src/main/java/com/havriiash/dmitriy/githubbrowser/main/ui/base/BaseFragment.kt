package com.havriiash.dmitriy.githubbrowser.main.ui.base

import dagger.android.support.DaggerFragment

abstract class BaseFragment<D>: DaggerFragment() {

    abstract fun showProgress(progress: Boolean)

    abstract fun showError(msg: String)

    abstract fun showContent(data: D)

}