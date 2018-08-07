package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import com.havriiash.dmitriy.githubbrowser.main.vm.NewsViewModel
import javax.inject.Inject

class NewsVMProviderFactory
    @Inject constructor(
            private val model: NewsModel
    ): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(model) as T
    }
}