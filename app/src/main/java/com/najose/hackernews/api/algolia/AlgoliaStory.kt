package com.najose.hackernews.api.algolia

import com.google.gson.annotations.SerializedName
import com.najose.hackernews.api.Comment
import com.najose.hackernews.api.Story

class AlgoliaStory : Story() {
    @SerializedName("num_comments")
    override val nComments: Long = super.nComments

    @SerializedName("created_at_i")
    override val createdAt: Long = super.createdAt

    @SerializedName("children")
    private val _comments: MutableList<AlgoliaComment> = mutableListOf()

    override suspend fun getComments(): List<Comment> = _comments.toList()
}