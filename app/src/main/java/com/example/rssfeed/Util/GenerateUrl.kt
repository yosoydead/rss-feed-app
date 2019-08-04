package com.example.rssfeed.Util

object GenerateUrl {
    //apple-music/coming-soon/all/10

    //order -> link + mediaType + feedType + genre + limit + extension
    private val link = "https://rss.itunes.apple.com/api/v1/us/"
    private val genre = "all"
    private val extension = "/non-explicit.rss"

    fun generate(mediaType: String, feedType: String, limit: String): String{
        return "$link$mediaType/$feedType/$genre/$limit$extension"
    }

}