package com.havriiash.dmitriy.githubbrowser.data.remote.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class Commit(
        @SerializedName("sha")
        val sha: String,
        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("commit")
        val commit: CommitData,
        @SerializedName("url")
        val url: String,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("comments_url")
        val commentsUrl: String,
        @SerializedName("author")
        val author: User,
        @SerializedName("commiter")
        val commiter: User,
        @SerializedName("parents")
        val parents: List<Parents>,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("files")
        val files: List<File>
) {
    data class CommitData(
            @SerializedName("author")
            val author: Author,
            @SerializedName("commiter")
            val commiter: Author,
            @SerializedName("message")
            val message: String,
            @SerializedName("tree")
            val tree: Tree,
            @SerializedName("url")
            val url: String,
            @SerializedName("comment_count")
            val commentCount: Int,
            @SerializedName("verification")
            val verification: Verification
    ) {
        data class Author(
                @SerializedName("name")
                val name: String,
                @SerializedName("email")
                val email: String,
                @SerializedName("date")
                val date: Date
        )

        data class Tree(
                @SerializedName("sha")
                val sha: String,
                @SerializedName("url")
                val url: String
        )

        data class Verification(
                @SerializedName("verified")
                val verified: Boolean,
                @SerializedName("reason")
                val reason: String
//              "signature": null,
//              "payload": null
        )
    }

    data class Parents(
            @SerializedName("sha")
            val sha: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("html_url")
            val htmlUrl: String
    )

    data class Stats(
            @SerializedName("total")
            val total: Int,
            @SerializedName("additions")
            val additions: Int,
            @SerializedName("deletions")
            val deletions: Int
    )

    data class File(
            @SerializedName("sha")
            val sha: String,
            @SerializedName("filename")
            val fileName: String,
            @SerializedName("status")
            val status: String,
            @SerializedName("additions")
            val additions: Int,
            @SerializedName("deletions")
            val deletions: Int,
            @SerializedName("changes")
            val changes: Int,
            @SerializedName("blob_url")
            val blobUrl: String,
            @SerializedName("raw_url")
            val rawUrl: String,
            @SerializedName("contents_url")
            val contentsUrl: String,
            @SerializedName("patch")
            val patch: String?
    )
}