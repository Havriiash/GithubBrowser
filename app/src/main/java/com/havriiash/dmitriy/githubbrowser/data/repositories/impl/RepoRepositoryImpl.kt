package com.havriiash.dmitriy.githubbrowser.data.repositories.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.Repository
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import io.reactivex.Single
import javax.inject.Inject

class RepoRepositoryImpl
@Inject constructor(
        private val githubApi: GithubApi,
        preferences: GithubBrowserPreferences
) : Repository(preferences), RepoRepository {

    override fun getUserRepos(userName: String, page: Int, count: Int): Single<List<Repo>> {
        return setSchedulers(githubApi.getUserRepos(userName, page, count, checkToken()))
    }

    override fun getRepoInfo(userName: String, repoName: String): Single<Repo> {
        return setSchedulers(githubApi.getUserRepo(userName, repoName, checkToken()))
    }

    override fun getRepoFiles(userName: String, repoName: String, path: String?): Single<List<Repo.File>> {
        return setSchedulers(githubApi.getRepoContents(userName, repoName, path, checkToken()))
    }

    override fun getRepoCommits(userName: String, repoName: String, page: Int, count: Int): Single<List<Commit>> {
        return setSchedulers(githubApi.getRepoCommits(userName, repoName, page, count, checkToken()))
    }

    override fun getCommitInfo(userName: String, repoName: String, shaId: String): Single<Commit> {
        return setSchedulers(githubApi.getRepoCommit(userName, repoName, shaId, checkToken()))
    }

    override fun getRepoEvents(userName: String, repoName: String, page: Int, count: Int): Single<List<User.UserActivity>> {
        return setSchedulers(githubApi.getRepoEvents(userName, repoName, page, count, checkToken()))
    }

    override fun getRepoStargazers(userName: String, repoName: String, page: Int, count: Int): Single<List<Follower>> {
        return setSchedulers(githubApi.getRepoStargazers(userName, repoName, page, count, checkToken()))
    }

    override fun getRepoWatchers(userName: String, repoName: String, page: Int, count: Int): Single<List<Follower>> {
        return setSchedulers(githubApi.getRepoContributors(userName, repoName, page, count, checkToken()))
    }

}