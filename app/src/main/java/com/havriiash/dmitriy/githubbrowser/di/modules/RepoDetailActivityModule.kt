package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.di.modules.repo.RepoDetailCommitsFragmentModule
import com.havriiash.dmitriy.githubbrowser.di.modules.repo.RepoDetailFilesFragmentModule
import com.havriiash.dmitriy.githubbrowser.di.modules.repo.RepoDetailInfoFragmentModule
import com.havriiash.dmitriy.githubbrowser.main.ui.RepoDetailActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.base.ContainerActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.base.IActivityContainer
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailCommitsFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailFilesFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailInfoFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.RepoDetailVMFactory
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module
abstract class RepoDetailActivityModule {

    @ActivityScope
    @Binds
    abstract fun bindRepoDetailViewModel(repoDetailViewModel: RepoDetailViewModel): ViewModel

    @ActivityScope
    abstract fun bindRepoDetailVMFactory(repoDetailVMFactory: RepoDetailVMFactory): ViewModelProvider.Factory

    @Module
    companion object {
        const val REPO_QUALIFIER_NAME = "repo_name"

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideUserName(repoDetailActivity: RepoDetailActivity): String =
                repoDetailActivity.intent.getStringExtra(RepoDetailActivity.USER_PARAM)

        @ActivityScope
        @JvmStatic
        @Provides
        @Named(REPO_QUALIFIER_NAME)
        fun provideRepoName(repoDetailActivity: RepoDetailActivity): String =
                repoDetailActivity.intent.getStringExtra(RepoDetailActivity.REPO_PARAM)

    }

    @ActivityScope
    @Binds
    abstract fun bindRepoContainerListener(repoDetailActivity: RepoDetailActivity): IActivityContainer<Repo>

    @ActivityScope
    @Binds
    abstract fun bindRepoContainer(repoDetailActivity: RepoDetailActivity): ContainerActivity


    @FragmentScope
    @ContributesAndroidInjector(modules = [RepoDetailInfoFragmentModule::class])
    abstract fun repoDetailInfoFragment(): RepoDetailInfoFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepoDetailFilesFragmentModule::class])
    abstract fun repoDetailFilesFragment(): RepoDetailFilesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepoDetailCommitsFragmentModule::class])
    abstract fun repoDetailCommitsFragment(): RepoDetailCommitsFragment

}