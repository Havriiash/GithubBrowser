package com.havriiash.dmitriy.githubbrowser.data.remote.entity

import com.google.gson.annotations.SerializedName

data class Organization(
        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("repos_url")
        val reposUrl: String,
        @SerializedName("events_url")
        val eventsUrl: String,
        @SerializedName("hooks_url")
        val hooksUrl: String,
        @SerializedName("issues_url")
        val issuesUrl: String,
        @SerializedName("members_url")
        val membersUrl: String,
        @SerializedName("public_members_url")
        val publicMembersUrl: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("description")
        val description: String
)