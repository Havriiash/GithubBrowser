package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.di.modules.CommitDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.CommitDetailModel
import com.havriiash.dmitriy.githubbrowser.main.vm.CommitDetailViewModel
import javax.inject.Inject
import javax.inject.Named

class CommitDetailVMFactory
@Inject constructor(
        private val model: CommitDetailModel,

        @Named(CommitDetailActivityModule.USER_NAME_QUALIFIER)
        private val userName: String,

        @Named(CommitDetailActivityModule.REPO_NAME_QUALIFIER)
        private val repoName: String,

        @Named(CommitDetailActivityModule.SHA_ID_QUALIFIER)
        private val shaId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommitDetailViewModel(model, userName, repoName, shaId) as T
    }
}