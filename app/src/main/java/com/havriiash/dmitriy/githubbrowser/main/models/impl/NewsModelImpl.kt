package com.havriiash.dmitriy.githubbrowser.main.models.impl

import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsModelImpl
    @Inject constructor(
            private val githubApi: GithubApi,
            private val preferences: GithubBrowserPreferences
    ): NewsModel {

    override fun getNews(page: Int, count: Int): Single<List<News>> {
        return githubApi.getNews(preferences.loggedUser?.login!!, page, count, preferences.accessToken!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}