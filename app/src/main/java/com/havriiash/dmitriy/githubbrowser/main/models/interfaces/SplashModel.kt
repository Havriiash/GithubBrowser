package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface SplashModel : ModelLayer {
    fun authorize(code: String): Single<AuthResponse>
}