package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface RepoDetailFilesModel : ModelLayer {
    fun getRepoFiles(userName: String, repoName: String, path: String?): Single<List<Repo.File>>
}