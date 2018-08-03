package com.havriiash.dmitriy.githubbrowser.main.models

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import io.reactivex.Single

interface MainModel : ModelLayer {
    fun getUserInfo(token: String): Single<User>
}