package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.SplashModel
import com.havriiash.dmitriy.githubbrowser.main.vm.SplashViewModel
import javax.inject.Inject

class SplashVMProviderFactory
    @Inject constructor(
            private val splashModel: SplashModel,
            private val preferences: GithubBrowserPreferences
    ): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(splashModel, preferences) as T
    }

}