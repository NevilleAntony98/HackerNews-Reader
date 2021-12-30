package com.najose.hackernews.api

import com.najose.hackernews.api.algolia.Algolia
import com.najose.hackernews.api.official.Official

interface Api {
    companion object {
        val FRONT_PAGE_PROVIDER = Official
        val COMMENTS_PROVIDER = Algolia
    }

    suspend fun getStory(storyId: Long): Story
}