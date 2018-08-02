package com.havriiash.dmitriy.spdilib

import dagger.android.DaggerApplication

abstract class BaseDaggerApp : DaggerApplication() {

    companion object {
        lateinit var instance: BaseDaggerApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initialize()
    }

    abstract fun initialize()
}