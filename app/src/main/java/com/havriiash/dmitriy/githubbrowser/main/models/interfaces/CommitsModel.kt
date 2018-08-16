package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface CommitsModel : ModelLayer {
    fun getCommits(userName: String, repoName: String, page: Int, count: Int): Single<List<Commit>>
}