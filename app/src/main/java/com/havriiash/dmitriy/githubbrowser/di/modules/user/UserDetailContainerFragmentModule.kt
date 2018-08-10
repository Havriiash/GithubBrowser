package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.models.impl.UserDetailModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailModel
import com.havriiash.dmitriy.githubbrowser.main.ui.base.FragmentContainerListener
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailActivityFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailInfoFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailStarredFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailVMFactory
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
    abstract fun bindUserDetailModel(userDetailModelImpl: UserDetailModelImpl): UserDetailModel

    @FragmentScope
    @Binds
    abstract fun bindUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun bindUserDetailVMFactory(userDetailVMFactory: UserDetailVMFactory): ViewModelProvider.Factory

    @Module
    companion object {
        @FragmentScope
        @JvmStatic
        @Provides
        fun provideUserName(userDetailContainerFragment: UserDetailContainerFragment): String {
            return userDetailContainerFragment.arguments?.getString(UserDetailContainerFragment.USER_PARAM, "")!!
        }
    }

    @FragmentScope
    @Binds
    abstract fun bindContainerListener(userDetailContainerFragment: UserDetailContainerFragment): FragmentContainerListener<User>


    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [UserDetailFragmentModule::class])
    abstract fun userDetailFragment(): UserDetailInfoFragment

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [UserDetailStarredFragmentModule::class])
    abstract fun userDetailStarredFragment(): UserDetailStarredFragment

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [UserDetailActivityFragmentModule::class])
    abstract fun userDetailActivityFragment(): UserDetailActivityFragment

}