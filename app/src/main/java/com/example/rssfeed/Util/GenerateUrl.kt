package com.example.rssfeed.Util

//this object is used to generate the links needed to download the xml
//as development goes by, these links will be modified based on some chip menus or something in the UI
object GenerateUrl {
    //apple-music/coming-soon/all/10

    //order -> link + mediaType + feedType + genre + limit + extension
    private val link = "https://rss.itunes.apple.com/api/v1/us/"
    private val genre = "all"


    fun generateRSS(mediaType: String, feedType: String, limit: String): String{
        val extension = "/non-explicit.rss"
        return "$link$mediaType/$feedType/$genre/$limit$extension"
    }

    fun generateATOM(mediaType: String, feedType: String, limit: String): String{
        val extension = "/explicit.atom"
        return "$link$mediaType/$feedType/$genre/$limit$extension"
    }

}