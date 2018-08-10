package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailStarredModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailStarredModelImpl
@Inject constructor(
        private val githubApi: GithubApi,
        private val preferences: GithubBrowserPreferences
) : UserDetailStarredModel {

    override fun getStarred(userName: String, page: Int, count: Int): Single<List<User.Starred>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getStarred(userName, page, count, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }
}