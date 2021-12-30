package com.najose.hackernews.api.official

import com.google.gson.annotations.SerializedName
import com.najose.hackernews.api.Api
import com.najose.hackernews.api.Comment
import com.najose.hackernews.api.Story

class HNStory : Story() {
    @SerializedName("by")
    override val author: String = super.author

    @SerializedName("descendants")
    override val nComments: Long = super.nComments

    @SerializedName("time")
    override val createdAt: Long = super.createdAt

    @SerializedName("score")
    override val points: Long = super.points

    override suspend fun getComments(): List<Comment> =
        Api.COMMENTS_PROVIDER.getStory(id).getComments()
}