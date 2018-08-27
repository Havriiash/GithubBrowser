package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import com.havriiash.dmitriy.githubbrowser.data.repositories.Repository
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.SplashModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashModelImpl
@Inject constructor(
        private val githubApi: GithubApi,
        preferences: GithubBrowserPreferences
) : Repository(preferences), SplashModel {

    override fun authorize(code: String): Single<AuthResponse> {
        return githubApi.authorize(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}