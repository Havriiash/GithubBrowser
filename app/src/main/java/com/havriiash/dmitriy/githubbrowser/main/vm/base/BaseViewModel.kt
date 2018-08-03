package com.havriiash.dmitriy.githubbrowser.main.vm.base

import android.arch.lifecycle.ViewModel
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<M : ModelLayer>(
        protected val model: M
) : ViewModel() {

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}