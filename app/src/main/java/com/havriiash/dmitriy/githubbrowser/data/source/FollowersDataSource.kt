package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowersModel
import javax.inject.Inject

class FollowersDataSource
@Inject constructor(
        model: FollowersModel,
        private val userName: String
) : BaseListDataSource<Follower, FollowersModel>(model) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Follower>) {
        currentPage = 1
        disposables.add(
                model.getFollowers(userName, 1, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { followers ->
                                    sourceObservable.value = RemoteResource.success(followers)
                                    callback.onResult(followers, params.requestedStartPosition)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Follower>) {
        ++currentPage
        disposables.add(
                model.getFollowers(userName, currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { followers ->
                                    sourceObservable.value = RemoteResource.success(followers)
                                    callback.onResult(followers)
                                },
                                { throwable ->
                                    sourceObservable.value = RemoteResource.error(throwable)
                                    --currentPage
                                }
                        )
        )
    }

}