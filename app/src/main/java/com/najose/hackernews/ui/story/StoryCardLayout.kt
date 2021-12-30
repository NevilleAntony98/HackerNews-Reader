package com.najose.hackernews.ui.story

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.najose.hackernews.data.StoryProvider

abstract class StoryCardLayout(view: View) : RecyclerView.ViewHolder(view) {
    abstract val view: View
    abstract val title: String
    abstract val author: String
    abstract val points: Long
    abstract val nComments: Long
    abstract val host: String
    abstract val time: Long

    abstract fun bind(storyContainer: StoryProvider.StoryContainer)

    var storyContainer: StoryProvider.StoryContainer? = null

    fun onAttached(adapter: RecyclerView.Adapter<StoryCardLayout>) {
        storyContainer?.addOnChangeListener(hashCode()) {
            adapter.notifyItemChanged(adapterPosition)
        }
    }

    fun onDetached() {
        storyContainer?.removeChangeListener(hashCode())
        storyContainer = null
    }
}