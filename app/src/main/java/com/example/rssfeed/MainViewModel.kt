package com.example.rssfeed

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeed.Model.Song
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

class MainViewModel: ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)
    val link = "https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/10/non-explicit.rss"

    private val _show = MutableLiveData<Int>()
    val show: LiveData<Int>
        get() = _show

    private val _data = MutableLiveData<String>()

    val data: LiveData<String>
        get() = _data

    private val _songs = MutableLiveData<ArrayList<Song>>()
    val songs: LiveData<ArrayList<Song>>
        get() = _songs

    init {
        _songs.value = arrayListOf<Song>()

        Log.d("ViewModel", "VIEW MODEL CREATED")
        _show.value = View.VISIBLE
        scope.launch {
            _data.value = async(Dispatchers.IO){
                downloadXML(link)
            }.await()
//            launch(Dispatchers.IO){
//                doStuff()
//            }

            //Log.d("ViewModel data", "${_data.value}")
            _songs.value = parseXML(_data.value!!)
            Log.d("ViewModel new LIST", "${_songs.value}")
            _show.value = View.GONE
        }
    }

    private suspend fun downloadXML(urlPath: String): String{
        val xmlResult = StringBuilder()

        try {
//            val url = URL(urlPath)
//
//            val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
//
//            val response = connection.responseCode
//            Log.i("Response code", response.toString())
//
//            connection.inputStream.buffered().reader().use {
//                reader -> xmlResult.append(reader.readText())
//            }
            val url = URL(urlPath)

            val urlConn: URLConnection = url.openConnection()

            val httpsConn: HttpsURLConnection = urlConn as HttpsURLConnection
            httpsConn.allowUserInteraction = false
            httpsConn.instanceFollowRedirects = true
            httpsConn.requestMethod = "GET"
            httpsConn.connect()

            val resCode = httpsConn.responseCode

            if(resCode == HttpURLConnection.HTTP_OK){
               Log.d("DOWNLOAD", "am reusit sa descarc")
                httpsConn.inputStream.buffered().reader().use {
                        reader->xmlResult.append(reader.readText())
                }

            }
            //Log.d("DOWNLOAD", xmlResult.toString())
            delay(3000)
            return xmlResult.toString()
        }catch (e: Exception){

            val errorMessage: String = when(e){
                is MalformedURLException -> "downloadXML: invalid url ${e.message}"
                is IOException -> "downloadXML: IO Exception reading data: ${e.message}"
                is SecurityException -> "downloadXML: security exception. Needs permision? ${e.message}"
                else -> "Unknown error: ${e.message}"
            }

            Log.e("EROARE", errorMessage)
        }

        return ""
    }


    private fun parseXML(xmlData: String): ArrayList<Song>?{
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
                        Log.d("PARSE", "starting tag: $tagName")
                        if(tagName == "item"){
                            inItem = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text
                    XmlPullParser.END_TAG ->{
                        if(inItem){
                            when(tagName){
                                "item" -> {
                                    list?.add(currentItem)
                                    inItem = false
                                    currentItem = Song()
                                }
                                "title" -> currentItem.title = textValue//Log.d("TITLE", textValue)
                                "category" -> Log.d("CATEGORY", textValue)
                                "description"-> currentItem.artist = textValue//Log.d("DESCRIPTION", textValue)
                                "pubdate" -> currentItem.published = textValue//Log.d("PUBDATE", textValue)
                            }
                        }
                        Log.d("PARSE", "ending tag: $tagName")
                    }
                }
//                if(eventType == XmlPullParser.START_DOCUMENT){
//                    Log.d("PARSER", "started document")
//                }else if(eventType ==XmlPullParser.START_TAG){
//                    Log.d("PARSER", "TAG START: $tagName")
//                }else if(eventType == XmlPullParser.END_TAG){
//                    Log.d("PARSER", "TAG END: $tagName")
//                }else if(eventType == XmlPullParser.TEXT){
//                    Log.d("PARSER", "TEXT: ${xpp.text}")
//                }

                eventType = xpp.next()
            }
        }catch(e: Exception){
            e.printStackTrace()
            Log.e("PARSER", "ERROR: ${e.message}")
        }

        Log.d("ViewModel PARSER", "$list")
        return list
    }

}