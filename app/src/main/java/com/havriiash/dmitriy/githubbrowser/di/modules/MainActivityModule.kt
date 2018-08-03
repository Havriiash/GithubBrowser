package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.MainModel
import com.havriiash.dmitriy.githubbrowser.main.models.MainModelImpl
import com.havriiash.dmitriy.githubbrowser.main.ui.NewsFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.MainVMProviderFactory
import com.havriiash.dmitriy.githubbrowser.main.vm.MainViewModel
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ActivityScope
    @Binds
    fun bindMainModel(mainModelImpl: MainModelImpl): MainModel

    @ActivityScope
    @Binds
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @ActivityScope
    @Binds
    fun bindMainViewModelFactory(mainViewModelVMProviderFactory: MainVMProviderFactory): ViewModelProvider.Factory


    @FragmentScope
    @ContributesAndroidInjector(modules = [NewFragmentModule::class])
    fun newsFragment(): NewsFragment

}