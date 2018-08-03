package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.SplashModel
import com.havriiash.dmitriy.githubbrowser.main.models.SplashModelImpl
import com.havriiash.dmitriy.githubbrowser.main.vm.SplashVMProviderFactory
import com.havriiash.dmitriy.githubbrowser.main.vm.SplashViewModel
import com.havriiash.dmitriy.spdilib.keys.ViewModelKey
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SplashActivityModule {

    @ActivityScope
    @Binds
    fun bindSplashModel(splashModelImpl: SplashModelImpl) : SplashModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindViewModel(splashViewModel: SplashViewModel) : ViewModel

    @ActivityScope
    @Binds
    fun bindViewModelProvideFactory(factory: SplashVMProviderFactory) : ViewModelProvider.Factory
}