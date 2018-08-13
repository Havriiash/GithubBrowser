package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.IShortRepoInfo
import com.havriiash.dmitriy.githubbrowser.data.source.RepoDataSource
import com.havriiash.dmitriy.githubbrowser.main.models.impl.RepoModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoModel
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoListFragment
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepoListFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindRepoModel(repoModelImpl: RepoModelImpl): RepoModel

    @Binds
    abstract fun bindRepoDataSource(repoDataSource: RepoDataSource): PositionalDataSource<IShortRepoInfo>

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideUserName(repoListFragment: RepoListFragment): String {
            return repoListFragment.arguments?.getString(RepoListFragment.USER_PARAM, "")!!
        }
    }
}