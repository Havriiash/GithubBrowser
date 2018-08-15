package com.havriiash.dmitriy.githubbrowser.di.modules.repo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.impl.RepoDetailFilesModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.RepoDetailFilesModel
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoDetailFilesFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.RepoDetailFilesViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.RepoDetailFilesVMFactory
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class RepoDetailFilesFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindRepoDetailFilesModel(repoDetailFilesModelImpl: RepoDetailFilesModelImpl): RepoDetailFilesModel

    @FragmentScope
    @Binds
    abstract fun bindRepoDetailFilesViewModel(repoDetailFilesViewModel: RepoDetailFilesViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun bindRepoDetailFileVMFactory(repoDetailFileVMFactory: RepoDetailFilesVMFactory): ViewModelProvider.Factory

    @Module
    companion object {
        const val PATH_QUALIFIER_NAME = "repo_detail_file_path"

        @JvmStatic
        @FragmentScope
        @Provides
        @Named(PATH_QUALIFIER_NAME)
        fun providePath(repoDetailFilesFragment: RepoDetailFilesFragment): String {
            return repoDetailFilesFragment.arguments?.getString(RepoDetailFilesFragment.PATH_PARAM).let { "" }
        }
    }
}