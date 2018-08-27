package com.havriiash.dmitriy.githubbrowser.data.repositories

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.exceptions.UnauthorizedException

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
}