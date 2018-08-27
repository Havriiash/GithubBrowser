package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.havriiash.dmitriy.githubbrowser.di.modules.repo.RepoListFragmentModule
import com.havriiash.dmitriy.githubbrowser.di.modules.user.UserDetailContainerFragmentModule
import com.havriiash.dmitriy.githubbrowser.main.ui.MainActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.base.ContainerActivity
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowersFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowingFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.NewsFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.repo.RepoListFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.MainViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.MainVMProviderFactory
import com.havriiash.dmitriy.spdilib.scopes.ActivityScope
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ActivityScope
    @Binds
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @ActivityScope
    @Binds
    fun bindMainViewModelFactory(mainViewModelVMProviderFactory: MainVMProviderFactory): ViewModelProvider.Factory

    @ActivityScope
    @Binds
    fun bindContainerActivity(mainActivity: MainActivity): ContainerActivity


    @FragmentScope
    @ContributesAndroidInjector(modules = [NewsFragmentModule::class])
    fun newsFragment(): NewsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FollowersFragmentModule::class])
    fun followersFragment(): FollowersFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FollowingFragmentModule::class])
    fun followingFrragment(): FollowingFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserDetailContainerFragmentModule::class])
    fun userDetailContainerFragment(): UserDetailContainerFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepoListFragmentModule::class])
    fun repoListFragment(): RepoListFragment

}