package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailModel
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import javax.inject.Inject

class UserDetailVMFactory
@Inject constructor(
        private val model: UserDetailModel,
        private val userName: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailViewModel(model, userName) as T
    }
}