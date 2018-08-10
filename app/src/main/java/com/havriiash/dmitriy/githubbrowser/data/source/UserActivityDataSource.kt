package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailActivityModel
import javax.inject.Inject

class UserActivityDataSource
    @Inject constructor(
            model: UserDetailActivityModel,
            private val userName: String
    ): BaseListDataSource<User.UserActivity, UserDetailActivityModel>(model) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<User.UserActivity>) {
        currentPage = 1
        disposables.add(
                model.getUserActivity(userName, currentPage, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { activities ->
                                    sourceObservable.value = RemoteResource.success(activities)
                                    callback.onResult(activities, params.requestedStartPosition)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<User.UserActivity>) {
        ++currentPage
        disposables.add(
                model.getUserActivity(userName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { activities ->
                                    sourceObservable.value = RemoteResource.success(activities)
                                    callback.onResult(activities)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    --currentPage
                                }
                        )
        )
    }
}