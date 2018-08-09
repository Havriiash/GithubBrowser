package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailContainerModel
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailContainerViewModel
import javax.inject.Inject

class UserDetailContainerVMFactory
    @Inject constructor(
            private val model: UserDetailContainerModel,
            private val userName: String
    ): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailContainerViewModel(model, userName) as T
    }
}