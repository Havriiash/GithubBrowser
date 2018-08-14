package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoDetailModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoDetailModelImpl
    @Inject constructor(
            private val githubApi: GithubApi,
            private val preferences: GithubBrowserPreferences
    ): RepoDetailModel {

    override fun getRepoInfo(userName: String, repoName: String): Single<Repo> {
        val token =  preferences.accessToken
        if (token != null) {
            return githubApi.getUserRepo(userName, repoName, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}