package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.impl.UserDetailModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailModel
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.UserDetailFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailVMFactory
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UserDetailFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindUserDetailModel(userDetailModelImpl: UserDetailModelImpl):  UserDetailModel

    @FragmentScope
    @Binds
    abstract fun bindUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun bindUserDetailVMFactory(userDetailVMFactory: UserDetailVMFactory): ViewModelProvider.Factory

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideUserName(userDetailFragment: UserDetailFragment): String {
            return userDetailFragment.getUserName()
        }
    }
}