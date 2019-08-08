package com.example.rssfeed.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeed.Model.Song
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

/*
* ill use a single view model to store the state of each fragment because i dont want to waste resources
*       creating a view model for each fragment
*
* when picking another fragment, that fragment gets destroyed along side its view model if i were to make one
*
* this will expose some properties that the fragments can use to communicate and display the needed data
* */
class MainViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.d("VIEWMODEL", "cleared")
    }
    //this defines the scope in which a coroutine will be called
    private val uiScope = CoroutineScope(Dispatchers.Main)

    //val link = "https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/10/non-explicit.rss"
    private val _songsLink = MutableLiveData<String>()

    private val _songsXML = MutableLiveData<String>()
    val songsXML: LiveData<String>
        get() = _songsXML

    private val _songsList = MutableLiveData<ArrayList<Song>>()
    val songsList: LiveData<ArrayList<Song>>
        get() = _songsList

    private val _tvShowsLink = MutableLiveData<String>()

    private val _tvShowsXML = MutableLiveData<String>()
    val tvShowsXML: LiveData<String>
        get() = _tvShowsXML

    private val _tvShowsList = MutableLiveData<ArrayList<Song>>()
    val tvShowsList: LiveData<ArrayList<Song>>
        get() = _tvShowsList

    init {
        _songsList.value = arrayListOf<Song>()
    }

    //this function is called when a new xml link is generated and then
    //download that xml source in the background
    fun setXML(urlPath: String){
        if(urlPath != _songsLink.value){
            Log.d("VIEWMODEL", "urlPath: $urlPath")
            Log.d("VIEWMODEL", "_songLink: ${_songsLink.value}")
            uiScope.launch {
                _songsXML.value = withContext(Dispatchers.IO) { downloadXML(urlPath) }
            }
            _songsLink.value = urlPath
        }else{
            Log.d("VIEWMODEL","same songs link. Not Downloading")
        }
    }

    //this method is called whenever the xml string is downloaded and needs parsing
    fun setSongList(xmlData: String){
        uiScope.launch {
            _songsList.value = withContext(Dispatchers.IO){parseXML(xmlData)}
        }
    }

    //downloads the xml string from the internet
    private suspend fun downloadXML(urlPath: String): String{
        val xmlResult = StringBuilder()

        try {
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

    //parses the xml received and returns an arrayList full of song objects
    //if the xml string is not null
    private suspend fun parseXML(xmlData: String): ArrayList<Song>?{
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
                                "artist" -> currentItem.artist = "by $textValue"//Log.d("TITLE", textValue)//currentItem.title = textValue
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