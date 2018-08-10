package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface UserDetailActivityModel : ModelLayer {
    fun getUserActivity(userName: String, page: Int, count: Int): Single<List<User.UserActivity>>
}