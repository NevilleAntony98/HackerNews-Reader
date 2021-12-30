package com.najose.hackernews.ui.story

import android.view.View
import android.widget.TextView
import com.najose.hackernews.R
import com.najose.hackernews.data.StoryProvider
import com.najose.hackernews.utils.Utils.makeFriendlyTime

class DefaultStoryCard(override val view: View) : StoryCardLayout(view) {
    private val titleView: TextView = view.findViewById(R.id.default_story_card_title)
    private val authorView: TextView = view.findViewById(R.id.default_story_card_author)
    private val pointsView: TextView = view.findViewById(R.id.default_story_card_points)
    private val hostView: TextView = view.findViewById(R.id.default_story_card_host)
    private val timeView: TextView = view.findViewById(R.id.default_story_card_time)
    private val nCommentsView: TextView = view.findViewById(R.id.default_story_card_n_comments)

    override var title: String
        get() = titleView.text as String
        set(value) {
            titleView.text = value
        }
    override var author: String
        get() = authorView.text as String
        set(value) {
            authorView.text = value
        }

    override var points: Long
        get() = (pointsView.text as String).toLong()
        set(value) {
            pointsView.text = value.toString() + " " + if (value == 1L) "point" else "points"
        }

    override var nComments: Long
        get() = (nCommentsView.text as String).toLong()
        set(value) {
            nCommentsView.text = value.toString()
        }
    override var host: String
        get() = hostView.text as String
        set(value) {
            hostView.text = value
        }
    override var time: Long
        get() {
            throw IllegalAccessException("Nope don't")
        }
        set(value) {
            val current = System.currentTimeMillis() / 1000
            timeView.text = makeFriendlyTime(current - value)
        }

    override fun bind(storyContainer: StoryProvider.StoryContainer) {
        this.storyContainer = storyContainer
        storyContainer.apply {
            title = story.title
            author = story.author
            points = story.points
            host = story.urlHost()
            nComments = story.nComments
            time = story.createdAt
        }
    }

    companion object {

    }
}