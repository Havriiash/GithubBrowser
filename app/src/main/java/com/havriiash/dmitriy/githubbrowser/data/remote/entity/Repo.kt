package com.havriiash.dmitriy.githubbrowser.data.remote.entity

import com.google.gson.annotations.SerializedName

data class Repo(
        @SerializedName("id") val id: Long,
        @SerializedName("node_id") val node_id: String,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val full_name: String,
        @SerializedName("owner") val owner: Follower,
        @SerializedName("private") val isPrivate: Boolean,
        @SerializedName("html_url") val html_url: String,
        @SerializedName("description") val description: String,
        @SerializedName("fork") val fork: Boolean,
        @SerializedName("url") val url: String,
        @SerializedName("forks_url") val forks_url: String,
        @SerializedName("keys_urls_url") val keys_urls_url: String,
        @SerializedName("collaborators_url") val collaborators_url: String,
        @SerializedName("teams_url") val teams_url: String,
        @SerializedName("hooks_url") val hooks_url: String,
        @SerializedName("issue_events_url") val issue_events_url: String,
        @SerializedName("events_url") val events_url: String,
        @SerializedName("assignees_url") val assignees_url: String,
        @SerializedName("branches_url") val branches_url: String,
        @SerializedName("tags_url") val tags_url: String,
        @SerializedName("blobs_url") val blobs_url: String,
        @SerializedName("git_tags_url") val git_tags_url: String,
        @SerializedName("git_refs_url") val git_refs_url: String,
        @SerializedName("trees_url") val trees_url: String,
        @SerializedName("statuses_url") val statuses_url: String,
        @SerializedName("languages_url") val languages_url: String,
        @SerializedName("stargazers_url") val stargazers_url: String,
        @SerializedName("contributors_url") val contributors_url: String,
        @SerializedName("subscribers_url") val subscribers_url: String,
        @SerializedName("subscription_url") val subscription_url: String,
        @SerializedName("commits_url") val commits_url: String,
        @SerializedName("git_commits_url") val git_commits_url: String,
        @SerializedName("comments_url") val comments_url: String,
        @SerializedName("issue_comment_url") val issue_comment_url: String,
        @SerializedName("contents_url") val contents_url: String,
        @SerializedName("compare_url") val compare_url: String,
        @SerializedName("merges_url") val merges_url: String,
        @SerializedName("archive_url") val archive_url: String,
        @SerializedName("downloads_url") val downloads_url: String,
        @SerializedName("issues_url") val issues_url: String,
        @SerializedName("pulls_url") val pulls_url: String,
        @SerializedName("milestones_url") val milestones_url: String,
        @SerializedName("notifications_url") val notifications_url: String,
        @SerializedName("labels_url") val labels_url: String,
        @SerializedName("releases_url") val releases_url: String,
        @SerializedName("deployments_url") val deployments_url: String,
        @SerializedName("created_at") val created_at: String,
        @SerializedName("updated_at") val updated_at: String,
        @SerializedName("pushed_at") val pushed_at: String,
        @SerializedName("git_url") val git_url: String,
        @SerializedName("ssh_url") val ssh_url: String,
        @SerializedName("clone_url") val clone_url: String,
        @SerializedName("svn_url") val svn_url: String,
        @SerializedName("homepage") val homepage: String,
        @SerializedName("size") val size: Int,
        @SerializedName("stargazers_count") val stargazers_count: Int,
        @SerializedName("watchers_count") val watchers_count: Int,
        @SerializedName("language") val language: String,
        @SerializedName("has_issues") val has_issues: Boolean,
        @SerializedName("has_projects") val has_projects: Boolean,
        @SerializedName("has_downloads") val has_downloads: Boolean,
        @SerializedName("has_wiki") val has_wiki: Boolean,
        @SerializedName("has_pages") val has_pages: Boolean,
        @SerializedName("forks_count") val forks_count: Int,
        @SerializedName("mirror_url") val mirror_url: String,
        @SerializedName("archived") val archived: Boolean,
        @SerializedName("open_issues_count") val open_issues_count: Int,
        @SerializedName("license") val license: License,
        @SerializedName("forks") val forks: Int,
        @SerializedName("open_issues") val open_issues: Int,
        @SerializedName("watchers") val watchers: Int,
        @SerializedName("default_branch") val default_branch: String,
        @SerializedName("network_count") val network_count: Int,
        @SerializedName("subscribers_count") val subscribers_count: Int
): IShortRepoInfo {

    override fun getRepoId(): Long = id

    override fun getRepoNodeId(): String = node_id

    override fun getRepoName(): String = name

    override fun getRepoFullName(): String = full_name

    override fun getRepoOwner(): Follower = owner

    override fun getRepoIsPrivate(): Boolean = isPrivate

    override fun getRepoHtmlUrl(): String = html_url

    override fun getRepoDescription(): String = description

    override fun getRepoStargazersCount(): Int = stargazers_count

    override fun getRepoForksCount(): Int = forks_count

    override fun getRepoLanguage(): String = language

    data class License(
            @SerializedName("key") val key: String,
            @SerializedName("name") val name: String,
            @SerializedName("spdx_id") val spdx_id: String,
            @SerializedName("url") val url: String,
            @SerializedName("node_id") val node_id: String
    )
}