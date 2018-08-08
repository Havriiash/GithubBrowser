package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowingModel
import javax.inject.Inject

class FollowingDataSource
    @Inject constructor(
            model: FollowingModel,
            private val userName: String
    ): BaseListDataSource<Follower, FollowingModel>(model) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Follower>) {
        disposables.add(
                model.getFollowing(userName, 1, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { following ->
                                    sourceObservable.value = RemoteResource.success(following)
                                    if (params.placeholdersEnabled) {
                                        callback.onResult(following, params.requestedStartPosition, following.size)
                                    } else {
                                        callback.onResult(following, params.requestedStartPosition)
                                    }
                                },
                                { throwable-> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Follower>) {
        ++currentPage
        disposables.add(
                model.getFollowing(userName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { following ->
                                    sourceObservable.value = RemoteResource.success(following)
                                    callback.onResult(following)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

}