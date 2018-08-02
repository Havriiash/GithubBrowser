package com.havriiash.dmitriy.githubbrowser

import com.havriiash.dmitriy.githubbrowser.di.DaggerAppComponent
import com.havriiash.dmitriy.spdilib.BaseDaggerApp
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector

class GithubBrowserApp: BaseDaggerApp() {

    override fun initialize() {
        AndroidThreeTen.init(this)
    }

    override fun applicationInjector(): AndroidInjector<GithubBrowserApp> =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()

}