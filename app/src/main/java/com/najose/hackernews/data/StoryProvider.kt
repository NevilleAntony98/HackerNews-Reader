package com.najose.hackernews.data

import com.najose.hackernews.api.Story

typealias StoryInputFunc = (story: Story) -> Unit

interface StoryProvider {
    fun getStory(position: Int): StoryContainer

    fun getItemCount(): Int

    fun clearAllListeners()

    class StoryContainer(story: Story) {
        private val changeListeners = mutableMapOf<Int, StoryInputFunc>()

        var story: Story = story
            internal set(value) {
                field = value
                changeListeners.forEach { (_, func) -> func(field) }
            }

        fun addOnChangeListener(hash: Int, func: StoryInputFunc) = changeListeners.put(hash, func)

        fun removeChangeListener(hash: Int) = changeListeners.remove(hash)

        fun clearListeners() = changeListeners.clear()
    }
}