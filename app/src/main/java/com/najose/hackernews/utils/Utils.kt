package com.najose.hackernews.utils


object Utils {
    fun makeFriendlyTime(diffSeconds: Long): String {
        val minute = 60L
        val hour = minute * 60
        val day = hour * 24
        val week = day * 7
        val year = week * 52

        if (diffSeconds in 0 until hour)
            return "${diffSeconds / minute}m"

        if (diffSeconds in hour until day)
            return "${diffSeconds / hour}h"

        if (diffSeconds in day until week)
            return "${diffSeconds / day}d"

        if (diffSeconds in week until year)
            return "${diffSeconds / week}w"

        return "${diffSeconds / year}y"
    }
}