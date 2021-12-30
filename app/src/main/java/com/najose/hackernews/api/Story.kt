package com.najose.hackernews.api

import java.net.URL

abstract class Story : Item() {
    open val title: String = "···"
    open val nComments: Long = 0
    open val url: String = ""

    abstract suspend fun getComments(): List<Comment>

    override fun toString() = super.toString() + ", points: $points, title: $title, url: $url"

    fun urlHost(): String = if (url.isEmpty()) "" else URL(url).host ?: ""
}