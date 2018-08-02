package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.havriiash.dmitriy.githubbrowser.main.ui.MainActivity
import com.havriiash.dmitriy.githubbrowser.di.modules.MainActivityModule
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}