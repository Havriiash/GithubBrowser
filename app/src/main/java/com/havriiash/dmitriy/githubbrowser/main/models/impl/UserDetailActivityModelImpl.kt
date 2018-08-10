package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailActivityModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailActivityModelImpl
@Inject constructor(
        private val githubApi: GithubApi,
        private val preferences: GithubBrowserPreferences
) : UserDetailActivityModel {

    override fun getUserActivity(userName: String, page: Int, count: Int): Single<List<User.UserActivity>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getUserActivity(userName, page, count, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}