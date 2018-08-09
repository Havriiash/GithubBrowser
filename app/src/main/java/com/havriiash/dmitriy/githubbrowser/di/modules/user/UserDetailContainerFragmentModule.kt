package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.impl.UserDetailContainerModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailContainerModel
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailStarredFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailContainerViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailContainerVMFactory
import com.havriiash.dmitriy.spdilib.scopes.ChildFragmentScope
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserDetailContainerFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindUserDetailContainerModel(userDetailModelImpl: UserDetailContainerModelImpl): UserDetailContainerModel

    @FragmentScope
    @Binds
    abstract fun bindUserDetailContainerViewModel(userDetailViewModel: UserDetailContainerViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun bindUserDetailContainerVMFactory(userDetailVMFactory: UserDetailContainerVMFactory): ViewModelProvider.Factory

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideUserName(userDetailContainerFragment: UserDetailContainerFragment): String {
            return userDetailContainerFragment.arguments?.getString(UserDetailContainerFragment.USER_PARAM, "")!!
        }
    }


    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [UserDetailFragmentModule::class])
    abstract fun userDetailFragment(): UserDetailFragment

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [UserDetailStarredFragmentModule::class])
    abstract fun userDetailStarredFragment(): UserDetailStarredFragment

}