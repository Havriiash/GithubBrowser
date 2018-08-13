package com.havriiash.dmitriy.githubbrowser.data.remote.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
        @SerializedName("login")
        val login: String,

        @SerializedName("id")
        val id: Long,

        @SerializedName("avatar_url")
        val avatarUrl: String,

        @SerializedName("gravatar_id")
        val gravatarId: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("html_url")
        val htmlUrl: String,

        @SerializedName("followers_url")
        val followersUrl: String,

        @SerializedName("following_url")
        val followingUrl: String,

        @SerializedName("gists_url")
        val gistsUrl: String,

        @SerializedName("starred_url")
        val starredUrl: String,

        @SerializedName("subscriptions_url")
        val subscriptionsUrl: String,

        @SerializedName("organizations_url")
        val organizationsUrl: String,

        @SerializedName("repos_url")
        val reposUrl: String,

        @SerializedName("events_url")
        val eventsUrl: String,

        @SerializedName("received_events_url")
        val receivedEventsUrl: String,

        @SerializedName("type")
        val type: String,

        @SerializedName("site_admin")
        val siteAdmin: Boolean,

        @SerializedName("name")
        val name: String,

        @SerializedName("company")
        val company: String,

        @SerializedName("blog")
        val blog: String,

        @SerializedName("location")
        val location: String,

        @SerializedName("email")
        val email: String,

        @SerializedName("hireable")
        val hireable: Any,

        @SerializedName("bio")
        val bio: String,

        @SerializedName("public_repos")
        val publicRepos: Int,

        @SerializedName("public_gists")
        val publicGists: Int,

        @SerializedName("followers")
        val followers: Int,

        @SerializedName("following")
        val following: Int,

        @SerializedName("created_at")
        val createdAt: Date,

        @SerializedName("updated_at")
        val updatedAt: Date
) {
    data class UserActivity(
            @SerializedName("id")
            val id: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("actor")
            val actor: Actor,
            @SerializedName("repo")
            val repo: Repo,
            //        paylod
            @SerializedName("public")
            val isPublic: Boolean,
            @SerializedName("created_at")
            val createdAt: Date
    ) {
        data class Actor(
                @SerializedName("id")
                val id: Long,
                @SerializedName("login")
                val login: String,
                @SerializedName("display_login")
                val displayLogin: String,
                @SerializedName("gravatar_id")
                val gravatarId: String,
                @SerializedName("url")
                val url: String,
                @SerializedName("avatar_url")
                val avatarUrl: String
        )

        data class Repo(
                @SerializedName("id")
                val id: Long,
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String
        )
    }

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
    ) : IShortRepoInfo {
        override fun getRepoId(): Long = id

        override fun getRepoNodeId(): String = nodeId

        override fun getRepoName(): String = name

        override fun getRepoFullName(): String = fullName

        override fun getRepoOwner(): Follower = owner

        override fun getRepoIsPrivate(): Boolean = isPrivate

        override fun getRepoHtmlUrl(): String = htmlUrl

        override fun getRepoDescription(): String = description

        override fun getRepoStargazersCount(): Int = stargazersCount

        override fun getRepoForksCount(): Int = forks

        override fun getRepoLanguage(): String = language
    }
}