package com.havriiash.dmitriy.githubbrowser.data.repositories.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.data.repositories.Repository
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoRepositoryImpl
@Inject constructor(
        private val githubApi: GithubApi,
        preferences: GithubBrowserPreferences
): Repository(preferences), RepoRepository {

    override fun getUserRepos(userName: String, page: Int, count: Int): Single<List<Repo>> {
        return githubApi.getUserRepos(userName, page, count, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRepoInfo(userName: String, repoName: String): Single<Repo> {
        return githubApi.getUserRepo(userName, repoName, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRepoFiles(userName: String, repoName: String, path: String?): Single<List<Repo.File>> {
        return githubApi.getRepoContents(userName, repoName, path, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRepoCommits(userName: String, repoName: String, page: Int, count: Int): Single<List<Commit>> {
        return githubApi.getRepoCommits(userName, repoName, page, count, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCommitInfo(userName: String, repoName: String, shaId: String): Single<Commit> {
        return githubApi.getRepoCommit(userName, repoName, shaId, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}