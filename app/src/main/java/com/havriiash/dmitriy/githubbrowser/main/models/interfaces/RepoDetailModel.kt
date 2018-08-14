package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface RepoDetailModel: ModelLayer {
    fun getRepoInfo(userName: String, repoName: String): Single<Repo>
}