package com.havriiash.dmitriy.githubbrowser.di.modules.user

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.main.models.impl.UserDetailModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.UserDetailModel
import com.havriiash.dmitriy.githubbrowser.main.vm.UserDetailViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.UserDetailVMFactory
import com.havriiash.dmitriy.spdilib.scopes.ChildFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class UserDetailFragmentModule {

    @ChildFragmentScope
    @Binds
    abstract fun bindUserDetailModel(userDetailModelImpl: UserDetailModelImpl): UserDetailModel

    @ChildFragmentScope
    @Binds
    abstract fun bindUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

    @ChildFragmentScope
    @Binds
    abstract fun bindUserDetailVMFactory(userDetailVMFactory: UserDetailVMFactory): ViewModelProvider.Factory

}