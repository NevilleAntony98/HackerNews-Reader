package com.najose.hackernews.api

abstract class Comment : Item() {
    open val text: String? = null

    abstract fun getChildComments(): List<Comment>
}