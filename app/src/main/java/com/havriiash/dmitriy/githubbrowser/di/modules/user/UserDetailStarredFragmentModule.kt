package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.IShortRepoInfo
import com.havriiash.dmitriy.githubbrowser.data.source.StarredDataSource
import dagger.Binds
import dagger.Module

@Module
interface UserDetailStarredFragmentModule {

    @Binds
    fun bindStarredDataSource(followersDataSource: StarredDataSource): PositionalDataSource<IShortRepoInfo>

}