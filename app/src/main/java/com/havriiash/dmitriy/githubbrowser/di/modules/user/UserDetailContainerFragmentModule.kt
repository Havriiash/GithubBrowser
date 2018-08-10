package com.havriiash.dmitriy.githubbrowser.di.modules.user

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.ui.base.FragmentContainerListener
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailContainerFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailInfoFragment
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.user.UserDetailStarredFragment
import com.havriiash.dmitriy.spdilib.scopes.ChildFragmentScope
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserDetailContainerFragmentModule {

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

}