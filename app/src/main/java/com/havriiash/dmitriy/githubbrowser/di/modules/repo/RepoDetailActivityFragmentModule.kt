package com.havriiash.dmitriy.githubbrowser.di.modules.repo

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.source.RepoEventsDataSource
import dagger.Binds
import dagger.Module

@Module
interface RepoDetailActivityFragmentModule {

    @Binds
    fun bindRepoEventsDataSource(repoEventsDataSource: RepoEventsDataSource): PositionalDataSource<User.UserActivity>

}