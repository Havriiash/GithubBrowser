package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoModelImpl
    @Inject constructor(
            private val githubApi: GithubApi,
            private val preferences: GithubBrowserPreferences
    ): RepoModel {

    override fun getUserRepos(userName: String, page: Int, count: Int): Single<List<Repo>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getUserRepos(userName, page, count, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}