package com.havriiash.dmitriy.githubbrowser.main.vm

import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class NewsViewModel
@Inject constructor(model: NewsModel
) : BaseViewModel<NewsModel>(model)