package com.havriiash.dmitriy.githubbrowser.data.remote.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class News(
        @SerializedName("id")
        val id: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("actor")
        val actor: NewsActor,
        @SerializedName("repo")
        val repo: NewsRepo,
        @SerializedName("public")
        val isPublic: Boolean,
        @SerializedName("created_at")
        val createdAt: Date
)

data class NewsActor(
        @SerializedName("id")
        val id: Long,
        @SerializedName("login")
        val login: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)

data class NewsRepo(
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)