package com.havriiash.dmitriy.githubbrowser.data.repositories.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.Repository
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor(
        private val githubApi: GithubApi,
        preferences: GithubBrowserPreferences
) : Repository(preferences), UserRepository {

    override fun getUserInfo(token: String): Single<User> {
        return setSchedulers(githubApi.getUser(token))
    }

    override fun getUserByName(userName: String): Single<User> {
        return setSchedulers(githubApi.getUserByName(userName, checkToken()))
    }

    override fun getUserOrganizations(userName: String): Single<List<Organization>> {
        return setSchedulers(githubApi.getUserOrganizations(userName, checkToken()))
    }

    override fun getUserStarred(userName: String, page: Int, count: Int): Single<List<User.Starred>> {
        return setSchedulers(githubApi.getUserStarred(userName, page, count, checkToken()))
    }

    override fun getUserActivity(userName: String, page: Int, count: Int): Single<List<User.UserActivity>> {
        return setSchedulers(githubApi.getUserActivity(userName, page, count, checkToken()))
    }

    override fun getFollowers(userName: String, page: Int, count: Int): Single<List<Follower>> {
        return setSchedulers(githubApi.getFollowers(userName, page, count, checkToken()))
    }

    override fun getFollowing(userName: String, page: Int, count: Int): Single<List<Follower>> {
        return setSchedulers(githubApi.getFollowing(userName, page, count, checkToken()))
    }

}