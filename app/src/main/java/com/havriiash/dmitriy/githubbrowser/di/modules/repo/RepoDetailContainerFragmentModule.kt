package com.havriiash.dmitriy.githubbrowser.di.modules.repo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Repo
import com.havriiash.dmitriy.githubbrowser.main.models.impl.RepoDetailModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoDetailModel
import com.havriiash.dmitriy.githubbrowser.main.ui.base.FragmentContainerListener
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailFilesFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailInfoFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.RepoDetailVMFactory
import com.havriiash.dmitriy.spdilib.scopes.ChildFragmentScope
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module
abstract class RepoDetailContainerFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindRepoDetailModel(repoDetailModelImpl: RepoDetailModelImpl): RepoDetailModel

    @FragmentScope
    @Binds
    abstract fun bindRepoDetailViewModel(repoDetailViewModel: RepoDetailViewModel): ViewModel

    @FragmentScope
    abstract fun bindRepoDetailVMFactory(repoDetailVMFactory: RepoDetailVMFactory): ViewModelProvider.Factory

    @Module
    companion object {
        const val REPO_QUALIFIER_NAME = "repo_name"

        @FragmentScope
        @JvmStatic
        @Provides
        fun provideUserName(repoDetailContainerFragment: RepoDetailContainerFragment): String {
            return repoDetailContainerFragment.arguments?.getString(RepoDetailContainerFragment.USER_PARAM)!!
        }

        @FragmentScope
        @JvmStatic
        @Provides
        @Named(REPO_QUALIFIER_NAME)
        fun provideRepoName(repoDetailContainerFragment: RepoDetailContainerFragment): String {
            return repoDetailContainerFragment.arguments?.getString(RepoDetailContainerFragment.REPO_PARAM)!!
        }
    }

    @FragmentScope
    @Binds
    abstract fun bindRepoContainerListener(repoDetailContainerFragment: RepoDetailContainerFragment): FragmentContainerListener<Repo>


    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [RepoDetailInfoFragmentModule::class])
    abstract fun repoDetailInfoFragment(): RepoDetailInfoFragment

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [RepoDetailFilesFragmentModule::class])
    abstract fun repoDetailFilesFragment(): RepoDetailFilesFragment

}