package com.najose.hackernews.api.algolia

import com.google.gson.annotations.SerializedName
import com.najose.hackernews.api.Comment

class AlgoliaComment : Comment() {
    @SerializedName("children")
    private val _childComments: MutableList<AlgoliaComment> = mutableListOf()

    override fun getChildComments(): List<Comment> = _childComments.toList()
}