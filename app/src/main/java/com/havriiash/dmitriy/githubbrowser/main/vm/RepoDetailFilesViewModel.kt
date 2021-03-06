package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class RepoDetailFilesViewModel
@Inject constructor(
        model: RepoRepository,
        private val userName: String,
        private val repoName: String
) : BaseViewModel<RepoRepository>(model) {

    val repoFileObservable: MutableLiveData<RemoteResource<List<Repo.File>>> = MutableLiveData()

    fun getRepoFile(path: String?) {
        disposables.add(
                model.getRepoFiles(userName, repoName, path)
                        .doOnSubscribe { repoFileObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { files -> repoFileObservable.value = RemoteResource.success(files) },
                                { throwable -> repoFileObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }
}