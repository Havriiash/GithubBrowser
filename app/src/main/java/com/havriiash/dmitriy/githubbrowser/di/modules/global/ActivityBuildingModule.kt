package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.havriiash.dmitriy.githubbrowser.di.modules.NewsActivityModule
import com.havriiash.dmitriy.githubbrowser.di.modules.SplashActivityModule
import com.havriiash.dmitriy.githubbrowser.main.ui.NewsActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.SplashActivity
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NewsActivityModule::class])
    abstract fun newsActivity(): NewsActivity

}