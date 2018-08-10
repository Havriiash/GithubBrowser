package com.havriiash.dmitriy.githubbrowser.main.ui.base

interface FragmentContainerListener<D> {

    fun onDataLoaded(data: D)

    fun onProgress(progress: Boolean)

    fun onError(msg: String)

}