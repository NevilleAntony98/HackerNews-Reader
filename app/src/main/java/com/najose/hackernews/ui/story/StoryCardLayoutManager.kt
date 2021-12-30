package com.najose.hackernews.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import com.najose.hackernews.R

object StoryCardLayoutManager {
    fun createStoryCard(parent: ViewGroup): StoryCardLayout {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.default_story_card_layout, parent, false)

        return DefaultStoryCard(view)
    }
}