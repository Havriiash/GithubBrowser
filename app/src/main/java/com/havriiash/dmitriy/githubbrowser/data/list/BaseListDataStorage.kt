package com.havriiash.dmitriy.githubbrowser.data.list

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseListDataStorage<D, M : ModelLayer>(
        protected val model: M
) : PositionalDataSource<D>() {

    protected val disposables = CompositeDisposable()

    val storageObservable: MutableLiveData<RemoteResource<List<D>>> = MutableLiveData()

    fun dispose() {
        disposables.clear()
    }
}