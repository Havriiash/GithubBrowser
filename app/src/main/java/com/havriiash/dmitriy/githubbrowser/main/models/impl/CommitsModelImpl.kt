package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.CommitsModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommitsModelImpl
@Inject constructor(
        private val githubApi: GithubApi,
        private val preferences: GithubBrowserPreferences
) : CommitsModel {

    override fun getCommits(userName: String, repoName: String, page: Int, count: Int): Single<List<Commit>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getRepoCommits(userName, repoName, page, count, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}