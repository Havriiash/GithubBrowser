package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Starred
import com.havriiash.dmitriy.githubbrowser.data.source.StarredDataSource
import com.havriiash.dmitriy.githubbrowser.main.models.impl.UserDetailStarredModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailStarredModel
import com.havriiash.dmitriy.spdilib.scopes.ChildFragmentScope
import dagger.Binds
import dagger.Module

@Module
interface UserDetailStarredFragmentModule {

    @ChildFragmentScope
    @Binds
    fun bindStarredModel(starredModelImpl: UserDetailStarredModelImpl): UserDetailStarredModel


    @Binds
    fun bindStarredDataSource(followersDataSource: StarredDataSource): PositionalDataSource<Starred>

}