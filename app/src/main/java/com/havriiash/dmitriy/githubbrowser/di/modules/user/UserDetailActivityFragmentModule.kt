package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.source.UserActivityDataSource
import com.havriiash.dmitriy.githubbrowser.main.models.impl.UserDetailActivityModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailActivityModel
import com.havriiash.dmitriy.spdilib.scopes.ChildFragmentScope
import dagger.Binds
import dagger.Module

@Module
interface UserDetailActivityFragmentModule {

    @ChildFragmentScope
    @Binds
    fun bindUserActivityModel(userDetailActivityModelImpl: UserDetailActivityModelImpl): UserDetailActivityModel


    @Binds
    fun bindUserActivityDataSource(userActivityDataSource: UserActivityDataSource): PositionalDataSource<User.UserActivity>
}