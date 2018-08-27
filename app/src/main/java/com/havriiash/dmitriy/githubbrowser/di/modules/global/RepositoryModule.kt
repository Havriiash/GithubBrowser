package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.havriiash.dmitriy.githubbrowser.data.repositories.impl.RepoRepositoryImpl
import com.havriiash.dmitriy.githubbrowser.data.repositories.impl.UserRepositoryImpl
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.RepoRepository
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.UserRepository
import com.havriiash.dmitriy.spdilib.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @AppScope
    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @AppScope
    @Binds
    fun bindRepoRepository(repoRepositoryImpl: RepoRepositoryImpl): RepoRepository

}