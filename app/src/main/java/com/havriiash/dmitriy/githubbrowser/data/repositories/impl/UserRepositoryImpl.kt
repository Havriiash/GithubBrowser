package com.havriiash.dmitriy.githubbrowser.data.repositories.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.Repository
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor(
        private val githubApi: GithubApi,
        preferences: GithubBrowserPreferences
) : Repository(preferences), UserRepository {

    override fun getUserInfo(token: String): Single<User> {
        return githubApi.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserByName(userName: String): Single<User> {
        return githubApi.getUserByName(userName, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserOrganizations(userName: String): Single<List<Organization>> {
        return githubApi.getUserOrganizations(userName, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserStarred(userName: String, page: Int, count: Int): Single<List<User.Starred>> {
        return githubApi.getUserStarred(userName, page, count, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserActivity(userName: String, page: Int, count: Int): Single<List<User.UserActivity>> {
        return githubApi.getUserActivity(userName, page, count, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFollowers(userName: String, page: Int, count: Int): Single<List<Follower>> {
        return githubApi.getFollowers(userName, page, count, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFollowing(userName: String, page: Int, count: Int): Single<List<Follower>> {
        return githubApi.getFollowing(userName, page, count, checkToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}