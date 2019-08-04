package com.example.rssfeed.Model

data class Song(var title :String =  "",
                var artist: String = "",
                var published: String = "") {
    override fun toString(): String {
        return "Title: $title, Artist: $artist, Published on: $published"
    }
}