package com.havriiash.dmitriy.githubbrowser.main.models

import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainModelImpl
    @Inject constructor(
            private val githubApi: GithubApi
    ): MainModel {

    override fun getUserInfo(token: String): Single<User> {
        return githubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}