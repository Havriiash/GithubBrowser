package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface RepoModel: ModelLayer {
    fun getUserRepos(userName: String, page: Int, count: Int): Single<List<Repo>>
}