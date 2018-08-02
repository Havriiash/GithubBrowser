package com.havriiash.dmitriy.githubbrowser.data.remote

import com.havriiash.dmitriy.githubbrowser.data.remote.responses.AuthResponse
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GithubApi {

    companion object {
        const val BASE_URL: String = "https://api.github.com/"

        const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize?client_id=${Credentials.CLIENT_ID}&scope=${Credentials.PERMISSIONS}"

        private object Credentials {
            const val CLIENT_ID = "ccc423918b31b976026d"
            const val CLIENT_SECRET = "8f47833d3bd2b6b8d94f47b3be06903f78689e5d"

            const val PERMISSIONS = "repo,gist,user"
        }
    }

//    =============================================================================================
//    Auth queries

    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept: application/json")
    fun authorize(@Query("code") code: String,
                  @Query("client_id") clientId: String = Credentials.CLIENT_ID,
                  @Query("client_secret") clientSecret: String = Credentials.CLIENT_SECRET): Single<AuthResponse>

//    =============================================================================================
//    Repository queries

//    =============================================================================================
//    User queries

//    =============================================================================================
//    Follows queries

//    =============================================================================================
//    Gists queries

//    =============================================================================================
//    Search queries

//    =============================================================================================
}