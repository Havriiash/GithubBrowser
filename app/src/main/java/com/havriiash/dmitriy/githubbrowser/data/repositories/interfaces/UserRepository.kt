package com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.IRepository
import io.reactivex.Single

interface UserRepository : IRepository {
    fun getUserInfo(token: String): Single<User>
    fun getUserByName(userName: String): Single<User>
    fun getUserOrganizations(userName: String): Single<List<Organization>>
    fun getUserStarred(userName: String, page: Int, count: Int): Single<List<User.Starred>>
    fun getUserActivity(userName: String, page: Int, count: Int): Single<List<User.UserActivity>>
    fun getFollowers(userName: String, page: Int, count: Int): Single<List<Follower>>
    fun getFollowing(userName: String, page: Int, count: Int): Single<List<Follower>>
}