package com.havriiash.dmitriy.githubbrowser.data.list

import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import javax.inject.Inject

class NewsDataStorage
@Inject constructor(
        newsModel: NewsModel
) : BaseListDataStorage<News, NewsModel>(newsModel) {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<News>) {
        disposables.add(
                model.getNews(1, params.pageSize)
                        .doOnSubscribe { storageObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { news ->
                                    storageObservable.value = RemoteResource.success(news)
                                    if (params.placeholdersEnabled) {
                                        callback.onResult(news, params.requestedStartPosition, news.size)
                                    } else {
                                        callback.onResult(news, params.requestedStartPosition)
                                    }
                                },
                                { throwable -> storageObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<News>) {
        disposables.add(
                model.getNews(params.startPosition, params.loadSize)
                        .doOnSubscribe { storageObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { news ->
                                    storageObservable.value = RemoteResource.success(news)
                                    callback.onResult(news)
                                },
                                { throwable -> storageObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

}