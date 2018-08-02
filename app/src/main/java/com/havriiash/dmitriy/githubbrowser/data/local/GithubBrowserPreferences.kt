package com.havriiash.dmitriy.githubbrowser.data.local

import android.content.Context
import com.google.gson.Gson
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User

class GithubBrowserPreferences(
        context: Context,
        private val gson: Gson
) {

    companion object {
        const val PREFERENCES_FILE_NAME = "GithubBrowser.prefs"

        private object Keys {
            const val ACCESS_TOKEN = "GithubBrowserPreference.Keys.access_token"
            const val USER = "GithubBrowserPreference.Keys.User"
        }
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    var accessToken: String?
    set(value) {
        preferences.edit()
                .putString(Keys.ACCESS_TOKEN, value)
                .apply()
    }
    get() = preferences.getString(Keys.ACCESS_TOKEN, null)

    var loggedUser: User?
    set(value) {
        preferences.edit()
                .putString(Keys.USER, gson.toJson(value))
                .apply()
    }
    get() {
        val userJson = preferences.getString(Keys.USER, null)
        return if (userJson != null) {
            gson.fromJson(userJson, User::class.java)
        } else {
            null
        }
    }

    fun clearPreferences() {
        accessToken = null
        loggedUser = null
    }
}