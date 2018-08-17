package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.CommitDetailModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommitDetailModelImpl
@Inject constructor(
        private val githubApi: GithubApi,
        private val preferences: GithubBrowserPreferences
) : CommitDetailModel {

    override fun getCommitInfo(userName: String, repoName: String, shaId: String): Single<Commit> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getRepoCommit(userName, repoName, shaId, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}