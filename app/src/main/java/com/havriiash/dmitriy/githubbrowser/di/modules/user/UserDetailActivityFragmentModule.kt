package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.source.UserActivityDataSource
import dagger.Binds
import dagger.Module

@Module
interface UserDetailActivityFragmentModule {

    @Binds
    fun bindUserActivityDataSource(userActivityDataSource: UserActivityDataSource): PositionalDataSource<User.UserActivity>

}