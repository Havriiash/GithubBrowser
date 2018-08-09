package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Starred
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface UserDetailStarredModel: ModelLayer {
    fun getStarred(userName: String, page: Int, count: Int): Single<List<Starred>>
}