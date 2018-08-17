package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface CommitDetailModel : ModelLayer {
    fun getCommitInfo(userName: String, repoName: String, shaId: String): Single<Commit>
}