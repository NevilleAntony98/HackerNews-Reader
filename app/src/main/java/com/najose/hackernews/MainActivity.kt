package com.najose.hackernews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.najose.hackernews.data.StoryViewModel
import com.najose.hackernews.ui.story.StoryListAdapter
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {
    private var storyListView: RecyclerView? = null
    private var storyListViewRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        storyListView = findViewById(R.id.main_activity_story_list_view)
        storyListView?.adapter = null
        storyListViewRefreshLayout = findViewById(R.id.main_activity_story_list_view_refresh_layout)

        storyListViewRefreshLayout?.setOnRefreshListener {
            StoryViewModel.refresh {
                onStoriesLoaded(it)

                storyListViewRefreshLayout?.isRefreshing = false
            }
        }

        storyListView?.layoutManager = LinearLayoutManager(this)
        StoryViewModel.loadStories(this::onStoriesLoaded)
    }

    private fun onStoriesLoaded(it: Exception?) {
        if (it is UnknownHostException) {
            Log.e("MainActivity", "Network Error")
            showSnackBar("Could not load stories due to network error")
        }

        storyListView?.adapter = StoryListAdapter(StoryViewModel)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(R.id.main_activity), message, Snackbar.LENGTH_INDEFINITE)
            .setAction("CLOSE") {}.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        storyListView?.layoutManager = null
        storyListView?.adapter = null
    }
}