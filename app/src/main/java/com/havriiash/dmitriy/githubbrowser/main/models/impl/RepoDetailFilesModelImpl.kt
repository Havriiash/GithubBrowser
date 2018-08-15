package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoDetailFilesModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoDetailFilesModelImpl
@Inject constructor(
        private val githubApi: GithubApi,
        private val preferences: GithubBrowserPreferences
) : RepoDetailFilesModel {

    override fun getRepoFiles(userName: String, repoName: String, path: String?): Single<List<Repo.File>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getRepoContents(userName, repoName, path, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}