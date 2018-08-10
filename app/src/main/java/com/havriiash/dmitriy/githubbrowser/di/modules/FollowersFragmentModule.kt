package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.source.FollowersDataSource
import com.havriiash.dmitriy.githubbrowser.main.models.impl.FollowersModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowersModel
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowersFragment
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class FollowersFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindFollowersModel(followersModelImpl: FollowersModelImpl): FollowersModel

    @FragmentScope
    @Binds
    abstract fun bindFollowersDataSource(followersDataSource: FollowersDataSource): PositionalDataSource<Follower>

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideUserName(followersFragment: FollowersFragment): String {
            return followersFragment.arguments?.getString(FollowersFragment.USER_PARAM, "")!!
        }
    }

}