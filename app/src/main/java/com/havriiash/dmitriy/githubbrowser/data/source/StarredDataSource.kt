package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.IShortRepoInfo
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.UserRepository
import javax.inject.Inject

class StarredDataSource
@Inject constructor(
        model: UserRepository,
        private val userName: String
) : BaseListDataSource<IShortRepoInfo, UserRepository>(model) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<IShortRepoInfo>) {
        currentPage = 1
        disposables.add(
                model.getUserStarred(userName, currentPage, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { starred ->
                                    sourceObservable.value = RemoteResource.success(starred)
                                    callback.onResult(starred, params.requestedStartPosition)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<IShortRepoInfo>) {
        ++currentPage
        disposables.add(
                model.getUserStarred(userName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { starred ->
                                    sourceObservable.value = RemoteResource.success(starred)
                                    callback.onResult(starred)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    --currentPage
                                }
                        )
        )
    }


}