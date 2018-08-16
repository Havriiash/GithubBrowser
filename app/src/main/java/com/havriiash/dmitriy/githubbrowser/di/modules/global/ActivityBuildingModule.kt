package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.havriiash.dmitriy.githubbrowser.di.modules.MainActivityModule
import com.havriiash.dmitriy.githubbrowser.di.modules.RepoDetailActivityModule
import com.havriiash.dmitriy.githubbrowser.di.modules.SplashActivityModule
import com.havriiash.dmitriy.githubbrowser.di.modules.WebBrowserActivityModule
import com.havriiash.dmitriy.githubbrowser.main.ui.MainActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.RepoDetailActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.SplashActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.WebBrowserActivity
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
    @ContributesAndroidInjector(modules = [WebBrowserActivityModule::class])
    fun webBrowserActivity(): WebBrowserActivity
}