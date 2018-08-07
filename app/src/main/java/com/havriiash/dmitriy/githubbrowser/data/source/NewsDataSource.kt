package com.havriiash.dmitriy.githubbrowser.data.source

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import javax.inject.Inject

class NewsDataSource
@Inject constructor(
        newsModel: NewsModel
) : BaseListDataSource<News, NewsModel>(newsModel) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<News>) {
        disposables.add(
                model.getNews(1, params.pageSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { news ->
                                    sourceObservable.value = RemoteResource.success(news)
                                    if (params.placeholdersEnabled) {
                                        callback.onResult(news, params.requestedStartPosition, news.size)
                                    } else {
                                        callback.onResult(news, params.requestedStartPosition)
                                    }
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<News>) {
        ++currentPage
        disposables.add(
                model.getNews(currentPage, params.loadSize)
                        .doOnSubscribe { sourceObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { news ->
                                    sourceObservable.value = RemoteResource.success(news)
                                    callback.onResult(news)
                                },
                                { throwable -> sourceObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

}