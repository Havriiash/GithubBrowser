package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import javax.inject.Inject
import javax.inject.Named

class RepoEventsDataSource
@Inject constructor(
        model: RepoRepository,
        private val userName: String
) : BaseListDataSource<User.UserActivity, RepoRepository>(model) {

    @field:[Inject Named(RepoDetailActivityModule.REPO_QUALIFIER_NAME)]
    protected lateinit var repoName: String

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<User.UserActivity>) {
        currentPage = 1
        disposables.add(
                model.getRepoEvents(userName, repoName, currentPage, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { events ->
                                    sourceObservable.value = RemoteResource.success(events)
                                    callback.onResult(events, 0)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<User.UserActivity>) {
        ++currentPage
        disposables.add(
                model.getRepoEvents(userName, repoName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { events ->
                                    sourceObservable.value = RemoteResource.success(events)
                                    callback.onResult(events)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    --currentPage
                                }
                        )
        )
    }

}