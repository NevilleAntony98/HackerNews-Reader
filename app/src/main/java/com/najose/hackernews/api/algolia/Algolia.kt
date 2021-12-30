package com.najose.hackernews.api.algolia

import com.najose.hackernews.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object Algolia : Api {
    private const val BASE_URL = "https://hn.algolia.com/api/v1/"

    private val endPoint by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AlgoliaEndPoint::class.java)
    }

    override suspend fun getStory(storyId: Long) = endPoint.getStory(storyId)

    private interface AlgoliaEndPoint {
        @GET("search?tags=front_page")
        suspend fun getFrontPage(
            @Query("page") page: Long
        ): FrontPageResponse

        @GET("items/{id}")
        suspend fun getStory(@Path("id") id: Long): AlgoliaStory

        companion object {
            class FrontPageResponse(
                val hits: List<AlgoliaStory>,
                val nbPages: Long,
            )
        }
    }
}

