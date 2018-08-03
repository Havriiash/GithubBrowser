package com.havriiash.dmitriy.githubbrowser.main.models

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import io.reactivex.Single

interface SplashModel : ModelLayer {
    fun authorize(code: String): Single<AuthResponse>

    fun getUser(token: String): Single<User>
}