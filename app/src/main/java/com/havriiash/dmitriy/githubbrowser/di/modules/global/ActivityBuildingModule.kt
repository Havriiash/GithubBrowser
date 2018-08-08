package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.havriiash.dmitriy.githubbrowser.di.modules.MainActivityModule
import com.havriiash.dmitriy.githubbrowser.di.modules.SplashActivityModule
import com.havriiash.dmitriy.githubbrowser.main.ui.MainActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.SplashActivity
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

}