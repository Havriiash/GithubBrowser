package com.havriiash.dmitriy.githubbrowser.main.models.interfaces

import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.data.repositories.IRepository
import io.reactivex.Single

interface NewsModel : IRepository {
    fun getNews(page: Int, count: Int): Single<List<News>>
}