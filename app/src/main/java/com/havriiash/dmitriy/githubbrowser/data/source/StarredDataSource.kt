package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Starred
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailStarredModel
import javax.inject.Inject

class StarredDataSource
    @Inject constructor(
            model: UserDetailStarredModel,
            private val userName: String
    ): BaseListDataSource<Starred, UserDetailStarredModel>(model) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Starred>) {
        currentPage = 1
        disposables.add(
                model.getStarred(userName, currentPage, params.pageSize)
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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Starred>) {
        ++currentPage
        disposables.add(
                model.getStarred(userName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { starred ->
                                    sourceObservable.value = RemoteResource.success(starred)
                                    callback.onResult(starred)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    currentPage = 1
                                }
                        )
        )
    }



}