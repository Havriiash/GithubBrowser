package com.havriiash.dmitriy.githubbrowser.di.modules.global

import android.content.Context
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.spdilib.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Module
    companion object {

        @AppScope
        @Provides
        fun provideAppPreferences(context: Context): GithubBrowserPreferences =
                GithubBrowserPreferences(
                        context = context,
                        gson = NetworkModule.provideGson()
                )

    }
}