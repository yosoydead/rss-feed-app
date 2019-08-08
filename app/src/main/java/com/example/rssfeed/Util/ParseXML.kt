package com.example.rssfeed.Util

import android.util.Log
import com.example.rssfeed.Model.Book
import com.example.rssfeed.Model.Movie
import com.example.rssfeed.Model.Song
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

object ParseXML {
    //parses the xml received and returns an arrayList full of song objects
    //if the xml string is not null
    suspend fun songsList(xmlData: String): ArrayList<Song>?{
        var inItem = false
        var textValue = ""
        var list = arrayListOf<Song>()

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())

            var currentItem = Song()

            var eventType = xpp.eventType
            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagName = xpp.name?.toLowerCase()

                when(eventType){
                    XmlPullParser.START_TAG -> {
                        //Log.d("PARSE", "starting tag: $tagName")
                        if(tagName == "entry"){
                            inItem = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text
                    XmlPullParser.END_TAG ->{
                        if(inItem){
                            when(tagName){
                                "entry" -> {
                                    list.add(currentItem)
                                    inItem = false
                                    currentItem = Song()
                                }
                                "artist" -> currentItem.artist = "By: $textValue"//Log.d("TITLE", textValue)//currentItem.title = textValue
                                "name" -> currentItem.title = textValue//Log.d("CATEGORY", xpp.getAttributeValue(0))
                                "image"-> currentItem.imgUrl = textValue//Log.d("DESCRIPTION", textValue)//currentItem.artist = textValue//
                                "releasedate" -> currentItem.published = "released: $textValue"//Log.d("PUBDATE", textValue)//currentItem.published = textValue//
                            }
                        }
                        //Log.d("PARSE", "ending tag: $tagName")
                    }
                }
                eventType = xpp.next()
            }
        }catch(e: Exception){
            e.printStackTrace()
            Log.e("PARSER", "ERROR: ${e.message}")
        }

        //Log.d("ViewModel PARSER", "$list")
        return list
    }

    suspend fun booksList(xmlData: String): ArrayList<Book>?{
        var inItem = false
        var textValue = ""
        var list = arrayListOf<Book>()

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())

            var currentItem = Book()

            var eventType = xpp.eventType
            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagName = xpp.name?.toLowerCase()

                when(eventType){
                    XmlPullParser.START_TAG -> {
                        //Log.d("PARSE", "starting tag: $tagName")
                        if(tagName == "entry"){
                            inItem = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text
                    XmlPullParser.END_TAG ->{
                        if(inItem){
                            when(tagName){
                                "entry" -> {
                                    list.add(currentItem)
                                    inItem = false
                                    currentItem = Book()
                                }
                                "artist" -> currentItem.writer = "Written by: $textValue"//Log.d("TITLE", textValue)//currentItem.title = textValue
                                "name" -> currentItem.title = textValue//Log.d("CATEGORY", xpp.getAttributeValue(0))
                                "image"-> currentItem.imgUrl = textValue//Log.d("DESCRIPTION", textValue)//currentItem.artist = textValue//
                                "releasedate" -> currentItem.published = "released: $textValue"//Log.d("PUBDATE", textValue)//currentItem.published = textValue//
                            }
                        }
                        //Log.d("PARSE", "ending tag: $tagName")
                    }
                }
                eventType = xpp.next()
            }
        }catch(e: Exception){
            e.printStackTrace()
            Log.e("PARSER", "ERROR: ${e.message}")
        }

        //Log.d("ViewModel PARSER", "$list")
        return list
    }

    suspend fun moviesList(xmlData: String): ArrayList<Movie>?{
        var inItem = false
        var textValue = ""
        var list = arrayListOf<Movie>()

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())

            var currentItem = Movie()

            var eventType = xpp.eventType
            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagName = xpp.name?.toLowerCase()

                when(eventType){
                    XmlPullParser.START_TAG -> {
                        //Log.d("PARSE", "starting tag: $tagName")
                        if(tagName == "entry"){
                            inItem = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text
                    XmlPullParser.END_TAG ->{
                        if(inItem){
                            when(tagName){
                                "entry" -> {
                                    list.add(currentItem)
                                    inItem = false
                                    currentItem = Movie()
                                }
                                "artist" -> currentItem.director = "Directed by: $textValue"//Log.d("TITLE", textValue)//currentItem.title = textValue
                                "name" -> currentItem.title = textValue//Log.d("CATEGORY", xpp.getAttributeValue(0))
                                "image"-> currentItem.imgUrl = textValue//Log.d("DESCRIPTION", textValue)//currentItem.artist = textValue//
                                "releasedate" -> currentItem.published = "released: $textValue"//Log.d("PUBDATE", textValue)//currentItem.published = textValue//
                            }
                        }
                        //Log.d("PARSE", "ending tag: $tagName")
                    }
                }
                eventType = xpp.next()
            }
        }catch(e: Exception){
            e.printStackTrace()
            Log.e("PARSER", "ERROR: ${e.message}")
        }

        //Log.d("ViewModel PARSER", "$list")
        return list
    }
}