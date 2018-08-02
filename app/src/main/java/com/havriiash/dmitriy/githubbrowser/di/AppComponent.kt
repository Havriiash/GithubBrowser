package com.havriiash.dmitriy.githubbrowser.di

import com.havriiash.dmitriy.githubbrowser.GithubBrowserApp
import com.havriiash.dmitriy.githubbrowser.di.modules.global.NetworkModule
import com.havriiash.dmitriy.githubbrowser.di.modules.global.ActivityBuildingModule
import com.havriiash.dmitriy.githubbrowser.di.modules.global.AppModule
import com.havriiash.dmitriy.githubbrowser.di.modules.global.PreferencesModule
import com.havriiash.dmitriy.spdilib.BaseAppComponent
import com.havriiash.dmitriy.spdilib.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
    AndroidSupportInjectionModule::class,

    ActivityBuildingModule::class,
    AppModule::class,
    NetworkModule::class,
    PreferencesModule::class
])
interface AppComponent : BaseAppComponent<GithubBrowserApp> {

    @Component.Builder
    interface AppComponentBuilder {
        @BindsInstance
        fun application(app: GithubBrowserApp): AppComponentBuilder

        fun build(): AppComponent
    }

}