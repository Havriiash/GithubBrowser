package com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.data.repositories.IRepository
import io.reactivex.Single

interface RepoRepository: IRepository {
    fun getUserRepos(userName: String, page: Int, count: Int): Single<List<Repo>>
    fun getRepoInfo(userName: String, repoName: String): Single<Repo>
    fun getRepoFiles(userName: String, repoName: String, path: String?): Single<List<Repo.File>>
    fun getRepoCommits(userName: String, repoName: String, page: Int, count: Int): Single<List<Commit>>
    fun getCommitInfo(userName: String, repoName: String, shaId: String): Single<Commit>
}