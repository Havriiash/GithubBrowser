package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.util.Log
import com.havriiash.dmitriy.githubbrowser.data.local.GithubBrowserPreferences
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.main.models.SplashModel
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
        splashModel: SplashModel,
        private val preferences: GithubBrowserPreferences
) : BaseViewModel<SplashModel>(splashModel) {

    val authorizeObserver: MutableLiveData<RemoteResource<String>> = MutableLiveData()

    val userObserver: MutableLiveData<RemoteResource<User>> = MutableLiveData()


    fun isAuthrorized(): Boolean {
        Log.d("SplashViewModel", "access_token=${preferences.accessToken}; user=${preferences.loggedUser?.login}")
        return preferences.accessToken != null && preferences.loggedUser != null
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

    fun getUserInfo(token: String) {
        disposables.add(
                model.getUser(token)
                        .doOnSubscribe { userObserver.value = RemoteResource.loading() }
                        .doAfterSuccess { user -> preferences.loggedUser = user }
                        .subscribe(
                                { user -> userObserver.value = RemoteResource.success(user) },
                                { throwable -> userObserver.value = RemoteResource.error(throwable) }
                        )
        )
    }
}