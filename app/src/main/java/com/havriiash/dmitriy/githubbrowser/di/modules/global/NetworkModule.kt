package com.havriiash.dmitriy.githubbrowser.di.modules.global

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.havriiash.dmitriy.githubbrowser.data.remote.GithubApi
import com.havriiash.dmitriy.githubbrowser.global.Constants
import com.havriiash.dmitriy.spdilib.scopes.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Module
    companion object {

        @JvmStatic
        @AppScope
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder()
                    .setLenient()
                    .setDateFormat(Constants.SERVER_DATE_FORMAT)
                    .create()
        }

        @JvmStatic
        @AppScope
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .build()
        }

        @JvmStatic
        @AppScope
        @Provides
        fun provideRetrofit(baseUrl: String,
                            gson: Gson,
                            okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
        }

        @JvmStatic
        @AppScope
        @Provides
        fun provideGithubApi(): GithubApi {
            return provideRetrofit(
                    GithubApi.BASE_URL,
                    provideGson(),
                    provideOkHttpClient()
            ).create(GithubApi::class.java)
        }

    }
}