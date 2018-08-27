package com.havriiash.dmitriy.githubbrowser.data.source

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.repositories.IRepository
import io.reactivex.disposables.CompositeDisposable

abstract class BaseListDataSource<D, M : IRepository>(
        protected val model: M
) : PositionalDataSource<D>() {

    protected val disposables = CompositeDisposable()

    val sourceObservable: MutableLiveData<RemoteResource<List<D>>> = MutableLiveData()

    var currentPage: Int = 1

    fun dispose() {
        disposables.clear()
    }
}