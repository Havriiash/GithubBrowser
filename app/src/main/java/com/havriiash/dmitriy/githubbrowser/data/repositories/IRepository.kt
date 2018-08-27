package com.havriiash.dmitriy.githubbrowser.data.repositories

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.exceptions.UnauthorizedException

interface IRepository {
    @Throws(InvalidTokenException::class)
    fun checkToken(): String

    @Throws(UnauthorizedException::class)
    fun checkUser(): User
}