package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.IShortRepoInfo
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import javax.inject.Inject

class RepoDataSource
@Inject constructor(
        model: RepoRepository,
        private val userName: String
) : BaseListDataSource<IShortRepoInfo, RepoRepository>(model) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<IShortRepoInfo>) {
        currentPage = 1
        disposables.add(
                model.getUserRepos(userName, currentPage, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { repos ->
                                    sourceObservable.value = RemoteResource.success(repos)
                                    callback.onResult(repos, 0)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<IShortRepoInfo>) {
        ++currentPage
        disposables.add(
                model.getUserRepos(userName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { repos ->
                                    sourceObservable.value = RemoteResource.success(repos)
                                    callback.onResult(repos)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    --currentPage
                                }
                        )
        )
    }
}