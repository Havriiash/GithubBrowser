package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.util.Log
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.SplashModel
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
        splashModel: SplashModel,
        private val preferences: GithubBrowserPreferences
) : BaseViewModel<SplashModel>(splashModel) {

    val authorizeObserver: MutableLiveData<RemoteResource<String>> = MutableLiveData()

    fun isAuthrorized(): Boolean {
        Log.d("SplashViewModel", "access_token=${preferences.accessToken}")
        return preferences.accessToken != null
    }

    fun parseIntent(githubIntent: Intent): String? {
        val response = githubIntent.data
        if (response != null) {
            val path = response.host
            if (path == "oauth") {
                return response.getQueryParameter("code")
            }
        }
        return null
    }

    fun authorize(code: String) {
        disposables.add(
                model.authorize(code)
                        .doOnSubscribe { authorizeObserver.value = RemoteResource.loading() }
                        .doAfterSuccess { authResponse -> preferences.accessToken = authResponse.accessToken }
                        .subscribe(
                                { authResponse -> authorizeObserver.value = RemoteResource.success(authResponse.accessToken) },
                                { throwable -> authorizeObserver.value = RemoteResource.error(throwable) }
                        )
        )
    }

}