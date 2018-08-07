package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.MainModel
import com.havriiash.dmitriy.githubbrowser.main.vm.MainViewModel
import javax.inject.Inject

class MainVMProviderFactory
    @Inject constructor(
            private val model: MainModel,
            private val preferences: GithubBrowserPreferences
    ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(model, preferences) as T
    }
}