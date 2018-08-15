package com.havriiash.dmitriy.githubbrowser.main.vm.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoDetailModel
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailViewModel
import javax.inject.Inject
import javax.inject.Named

class RepoDetailVMFactory
@Inject constructor(
        private val model: RepoDetailModel,
        private val userName: String,

        @Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)
        private val repoName: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoDetailViewModel(model, userName, repoName) as T
    }
}