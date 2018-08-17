package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.impl.CommitDetailModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.CommitDetailModel
import com.havriiash.dmitriy.githubbrowser.main.ui.CommitDetailActivity
import com.havriiash.dmitriy.githubbrowser.main.vm.CommitDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.CommitDetailVMFactory
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class CommitDetailActivityModule {

    @ActivityScope
    @Binds
    abstract fun bindCommitDetailModel(commitDetailModelImpl: CommitDetailModelImpl): CommitDetailModel

    @ActivityScope
    @Binds
    abstract fun bindCommitDetailViewModel(commitDetailViewModel: CommitDetailViewModel): ViewModel

    @ActivityScope
    @Binds
    abstract fun bindCommitDetailVMFactory(commitDetailVMFactory: CommitDetailVMFactory): ViewModelProvider.Factory


    @Module
    companion object {
        const val USER_NAME_QUALIFIER = "CommitDetailActivityModule.Qualifiers.UserName"
        const val REPO_NAME_QUALIFIER = "CommitDetailActivityModule.Qualifiers.RepoName"
        const val SHA_ID_QUALIFIER = "CommitDetailActivityModule.Qualifiers.ShaId"

        @JvmStatic
        @ActivityScope
        @Provides
        @Named(USER_NAME_QUALIFIER)
        fun provideUserName(commitDetailActivity: CommitDetailActivity): String {
            return commitDetailActivity.intent.getStringExtra(CommitDetailActivity.USER_NAME_PARAM)
        }

        @JvmStatic
        @ActivityScope
        @Provides
        @Named(REPO_NAME_QUALIFIER)
        fun provideRepoName(commitDetailActivity: CommitDetailActivity): String {
            return commitDetailActivity.intent.getStringExtra(CommitDetailActivity.REPO_NAME_PARAM)
        }

        @JvmStatic
        @ActivityScope
        @Provides
        @Named(SHA_ID_QUALIFIER)
        fun provideShaId(commitDetailActivity: CommitDetailActivity): String {
            return commitDetailActivity.intent.getStringExtra(CommitDetailActivity.SHA_ID_PARAM)
        }
    }
}