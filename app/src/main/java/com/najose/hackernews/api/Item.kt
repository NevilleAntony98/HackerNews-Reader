package com.najose.hackernews.api

abstract class Item {
    open val id: Long = 0
    open val author: String = "···"
    open val createdAt: Long = System.currentTimeMillis() / 1000
    open val points: Long = 0

    override fun hashCode() = id.hashCode()

    override fun equals(other: Any?) = other === this || (other is Item && other.id == id)

    override fun toString() = "id: $id, author: $author, createdAt: $createdAt"
}