package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.havriiash.dmitriy.githubbrowser.di.modules.*
import com.havriiash.dmitriy.githubbrowser.main.ui.*
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuildingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RepoDetailActivityModule::class])
    fun repoDetailActivity(): RepoDetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [CommitDetailActivityModule::class])
    fun commitDetailActivity(): CommitDetailActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [WebBrowserActivityModule::class])
    fun webBrowserActivity(): WebBrowserActivity
}