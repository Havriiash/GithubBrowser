package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.arch.lifecycle.LiveData
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource

interface IActivityContainer<D> {
    fun refreshInfo()

    fun getDataObservable(): LiveData<RemoteResource<D>>
}