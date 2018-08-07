package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface FollowersModel: ModelLayer {
    fun getFollowers(userName: String, page: Int, count: Int): Single<List<Follower>>
}