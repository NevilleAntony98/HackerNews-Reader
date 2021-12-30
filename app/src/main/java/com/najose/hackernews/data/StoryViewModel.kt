package com.najose.hackernews.data

import androidx.lifecycle.ViewModel
import com.najose.hackernews.api.Api
import com.najose.hackernews.api.algolia.AlgoliaStory
import com.najose.hackernews.api.official.Official
import kotlinx.coroutines.*
import java.util.*

object StoryViewModel : ViewModel(), StoryProvider {
    private var loaded = false
    private var mainStories =
        Collections.synchronizedList(mutableListOf<StoryProvider.StoryContainer>())
    private var currentJob: Job? = null
    private var jobsContainer = Collections.synchronizedList(mutableListOf<Job>())

    fun refresh(func: (exception: Exception?) -> Unit) {
        loaded = false
        mainStories.clear()

        jobsContainer.forEach { it.cancel() }
        jobsContainer.clear()
        currentJob?.cancel()

        loadStories(func)
    }

    fun loadStories(func: (exception: Exception?) -> Unit) {
        if (loaded)
            return func(null)

        currentJob = CoroutineScope(Dispatchers.Default).launch {
            val deferred = async(SupervisorJob()) {
                val storyIds = Official.getStoryIds()
                val stories = mutableListOf<StoryProvider.StoryContainer>()

                storyIds.forEachIndexed { index, id ->
                    stories.add(StoryProvider.StoryContainer(AlgoliaStory()))
                    val job = launch(Dispatchers.IO) {
                        val loadedStory = Api.FRONT_PAGE_PROVIDER.getStory(id)
                        withContext(Dispatchers.Main) {
                            stories[index].story = loadedStory
                        }
                    }

                    jobsContainer.add(job)
                }

                withContext(Dispatchers.Main) {
                    mainStories = stories
                    loaded = true
                    func(null)
                }
            }

            try {
                deferred.await()
            } catch (ex: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    func(ex)
                }
            } finally {
                jobsContainer.clear()
            }
        }
    }

    override fun getStory(position: Int): StoryProvider.StoryContainer = mainStories[position]

    override fun getItemCount(): Int = mainStories.size

    override fun clearAllListeners() = mainStories.forEach { it.clearListeners() }
}