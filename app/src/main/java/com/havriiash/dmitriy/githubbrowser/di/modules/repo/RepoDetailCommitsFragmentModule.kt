package com.havriiash.dmitriy.githubbrowser.di.modules.repo

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.data.source.CommitsDataSource
import dagger.Binds
import dagger.Module

@Module
interface RepoDetailCommitsFragmentModule {

    @Binds
    fun bindCommitsDataSource(commitsDataSource: CommitsDataSource): PositionalDataSource<Commit>

}