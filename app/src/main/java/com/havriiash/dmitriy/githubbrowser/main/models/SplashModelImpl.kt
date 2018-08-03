package com.havriiash.dmitriy.githubbrowser.main.models

import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashModelImpl
    @Inject constructor(
            private val githubApi: GithubApi
    ): SplashModel {

    override fun authorize(code: String): Single<AuthResponse> {
        return githubApi.authorize(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUser(token: String): Single<User> {
        return githubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}