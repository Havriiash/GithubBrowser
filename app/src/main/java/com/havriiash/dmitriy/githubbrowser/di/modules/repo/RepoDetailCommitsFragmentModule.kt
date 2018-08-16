package com.havriiash.dmitriy.githubbrowser.di.modules.repo

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.data.source.CommitsDataSource
import com.havriiash.dmitriy.githubbrowser.main.models.impl.CommitsModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.CommitsModel
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface RepoDetailCommitsFragmentModule {

    @FragmentScope
    @Binds
    fun bindCommitsModel(commitsModelImpl: CommitsModelImpl): CommitsModel

    @Binds
    fun bindCommitsDataSource(commitsDataSource: CommitsDataSource): PositionalDataSource<Commit>

}