package com.havriiash.dmitriy.githubbrowser.data.repositories

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.exceptions.UnauthorizedException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class Repository(
        private val preferences: GithubBrowserPreferences
) : IRepository {
    @Throws(InvalidTokenException::class)
    override fun checkToken(): String {
        val token = preferences.accessToken
        return token ?: throw InvalidTokenException()
    }

    @Throws(UnauthorizedException::class)
    override fun checkUser(): User {
        val user = preferences.loggedUser
        return user ?: throw UnauthorizedException()
    }

    override fun <T> setSchedulers(observable: Single<T>): Single<T> {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}