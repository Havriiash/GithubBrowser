package com.havriiash.dmitriy.githubbrowser.di.modules

import android.arch.paging.PositionalDataSource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.data.source.FollowingDataSource
import com.havriiash.dmitriy.githubbrowser.main.models.impl.FollowingModelImpl
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.FollowingModel
import com.havriiash.dmitriy.githubbrowser.main.ui.fragments.FollowingFragment
import com.havriiash.dmitriy.spdilib.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class FollowingFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindFollowingModel(followingModelImpl: FollowingModelImpl): FollowingModel

    @FragmentScope
    @Binds
    abstract fun bindFollowingDataSource(followingDataSource: FollowingDataSource): PositionalDataSource<Follower>


    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideUserName(followingFragment: FollowingFragment): String {
            return followingFragment.getUserName()
        }
    }

}