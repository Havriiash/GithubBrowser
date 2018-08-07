package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.source.NewsDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.main.models.impl.NewsModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import com.havriiash.dmitriy.githubbrowser.main.vm.NewsViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.NewsVMProviderFactory
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface NewsFragmentModule {

    @FragmentScope
    @Binds
    fun bindNewsModel(newsModelImpl: NewsModelImpl): NewsModel

    @FragmentScope
    @Binds
    fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @FragmentScope
    @Binds
    fun bindNewsVMFactory(newsVMProviderFactory: NewsVMProviderFactory): ViewModelProvider.Factory

    @FragmentScope
    @Binds
    fun bindNewsPositionalDataStorage(dataStorage: NewsDataSource): PositionalDataSource<News>

}