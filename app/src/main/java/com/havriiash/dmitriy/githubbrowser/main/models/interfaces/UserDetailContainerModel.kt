package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.models.ModelLayer
import io.reactivex.Single

interface UserDetailContainerModel : ModelLayer {
    fun getUserInfo(userName: String): Single<User>
    fun getUserOrganizations(userName: String): Single<List<Organization>>
}