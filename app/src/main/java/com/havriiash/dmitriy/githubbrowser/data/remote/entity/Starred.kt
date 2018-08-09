package com.havriiash.dmitriy.githubbrowser.data.remote.entity

import com.google.gson.annotations.SerializedName

data class Starred(
        @SerializedName("id")
        val id: Long,
        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("owner")
        val owner: Follower,
        @SerializedName("private")
        val isPrivate: Boolean,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("stargazers_count")
        val stargazersCount: Int,
        @SerializedName("forks")
        val forks: Int,
        @SerializedName("language")
        val language: String
)