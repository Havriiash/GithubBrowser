package com.havriiash.dmitriy.githubbrowser.data.remote.entity

interface IShortRepoInfo {

    fun getRepoId(): Long

    fun getRepoNodeId(): String

    fun getRepoName(): String

    fun getRepoFullName(): String

    fun getRepoOwner(): Follower

    fun getRepoIsPrivate(): Boolean

    fun getRepoHtmlUrl(): String

    fun getRepoDescription(): String

    fun getRepoStargazersCount(): Int

    fun getRepoForksCount(): Int

    fun getRepoLanguage(): String

}