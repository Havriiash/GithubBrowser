package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowingModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FollowingModelImpl
    @Inject constructor(
            private val githubApi: GithubApi,
            private val preferences: GithubBrowserPreferences
    ): FollowingModel {


    override fun getFollowing(userName: String, page: Int, count: Int): Single<List<Follower>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getFollowing(userName, page, count, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw RuntimeException("Invalid access token")
    }
}