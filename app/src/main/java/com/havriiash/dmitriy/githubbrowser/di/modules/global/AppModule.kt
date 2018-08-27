package com.havriiash.dmitriy.githubbrowser.di.modules.global

import android.content.Context
import com.havriiash.dmitriy.githubbrowser.GithubBrowserApp
import com.havriiash.dmitriy.spdilib.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @AppScope
    @Binds
    fun bindContext(app: GithubBrowserApp): Context

}