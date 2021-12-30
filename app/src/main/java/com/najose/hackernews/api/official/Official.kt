package com.najose.hackernews.api.official

import com.najose.hackernews.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object Official : Api {
    private const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

    private val endPoint by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(OfficialEndPoint::class.java)
    }

    suspend fun getStoryIds() = endPoint.getTopStories()

    override suspend fun getStory(storyId: Long) = endPoint.getStory(storyId)

    private interface OfficialEndPoint {
        @GET("topstories.json")
        suspend fun getTopStories(): List<Long>

        @GET("item/{id}.json")
        suspend fun getStory(@Path("id") id: Long): HNStory
    }
}