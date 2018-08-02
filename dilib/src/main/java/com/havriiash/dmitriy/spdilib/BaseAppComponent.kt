package com.havriiash.dmitriy.spdilib

import dagger.android.AndroidInjector

interface BaseAppComponent<T : BaseDaggerApp>: AndroidInjector<T> {
    override fun inject(instance: T?)
}