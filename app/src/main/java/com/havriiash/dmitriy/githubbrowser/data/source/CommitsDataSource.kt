package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.CommitsModel
import javax.inject.Inject
import javax.inject.Named

class CommitsDataSource
@Inject constructor(
        model: CommitsModel,
        private val userName: String
) : BaseListDataSource<Commit, CommitsModel>(model) {

    @field:[Inject Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)]
    protected lateinit var repoName: String

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Commit>) {
        currentPage = 1
        disposables.add(
                model.getCommits(userName, repoName, currentPage, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { commits ->
                                    sourceObservable.value = RemoteResource.success(commits)
                                    callback.onResult(commits, params.requestedStartPosition)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Commit>) {
        ++currentPage
        disposables.add(
                model.getCommits(userName, repoName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { commits ->
                                    sourceObservable.value = RemoteResource.success(commits)
                                    callback.onResult(commits)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    --currentPage
                                }
                        )
        )
    }
}