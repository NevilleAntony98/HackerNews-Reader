package com.najose.hackernews.ui.story

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.najose.hackernews.data.StoryProvider

class StoryListAdapter(private val storyProvider: StoryProvider) :
    RecyclerView.Adapter<StoryCardLayout>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StoryCardLayoutManager.createStoryCard(parent)

    override fun onBindViewHolder(holder: StoryCardLayout, position: Int) {
        holder.bind(storyProvider.getStory(position))
    }

    override fun onViewAttachedToWindow(holder: StoryCardLayout) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached(this)
    }

    override fun onViewDetachedFromWindow(holder: StoryCardLayout) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    override fun getItemCount() = storyProvider.getItemCount()

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        storyProvider.clearAllListeners()
    }
}

