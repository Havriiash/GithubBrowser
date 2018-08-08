package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.exceptions.InvalidTokenException
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailModelImpl
    @Inject constructor(
            private val githubApi: GithubApi,
            private val preferences: GithubBrowserPreferences
    ): UserDetailModel {

    override fun getUserInfo(userName: String): Single<User> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getUserByName(userName, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }

    override fun getUserOrganizations(userName: String): Single<List<Organization>> {
        val token = preferences.accessToken
        if (token != null) {
            return githubApi.getUserOrganizations(userName, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        throw InvalidTokenException()
    }

}