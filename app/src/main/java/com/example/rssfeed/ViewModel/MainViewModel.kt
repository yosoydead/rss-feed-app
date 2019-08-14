package com.example.rssfeed.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeed.Model.Book
import com.example.rssfeed.Model.Movie
import com.example.rssfeed.Model.Song
import com.example.rssfeed.Util.DownloadXML
import com.example.rssfeed.Util.ParseXML
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

    val _screenHeight = MutableLiveData<Float>()
//    val screenHeight: LiveData<Float>
//        get() = _screenHeight

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

    private val _booksLink = MutableLiveData<String>()

    private val _booksXML = MutableLiveData<String>()
    val booksXML: LiveData<String>
        get() = _booksXML

    private val _booksList = MutableLiveData<ArrayList<Book>>()
    val booksList: LiveData<ArrayList<Book>>
        get() = _booksList

    private val _moviesLink = MutableLiveData<String>()

    private val _moviesXML = MutableLiveData<String>()
    val moviesXML: LiveData<String>
        get() = _moviesXML

    private val _moviesList = MutableLiveData<ArrayList<Movie>>()
    val moviesList: LiveData<ArrayList<Movie>>
        get() = _moviesList

    init {
        _songsList.value = arrayListOf<Song>()
        _booksList.value = arrayListOf<Book>()
        _moviesList.value = arrayListOf<Movie>()
    }

    //this function is called when a new xml link is generated and then
    //download that xml source in the background
    fun setSongsXML(urlPath: String){
        if(urlPath != _songsLink.value){
//            Log.d("VIEWMODEL", "urlPath: $urlPath")
//            Log.d("VIEWMODEL", "_songLink: ${_songsLink.value}")
            uiScope.launch {
                _songsXML.value = withContext(Dispatchers.IO) { DownloadXML.downloadXML(urlPath) }
            }
            _songsLink.value = urlPath
        }else{
            Log.d("VIEWMODEL","same songs link. Not Downloading")
        }
    }

    //this method is called whenever the xml string is downloaded and needs parsing
    fun setSongList(xmlData: String){
        uiScope.launch {
            _songsList.value = withContext(Dispatchers.IO){ ParseXML.songsList(xmlData) }
        }
    }

    fun setBooksXML(urlPath: String){
        if(urlPath != _booksLink.value){
            uiScope.launch {
                _booksXML.value = withContext(Dispatchers.IO) { DownloadXML.downloadXML(urlPath) }
            }
            _booksLink.value = urlPath
        }else{
            Log.d("VIEWMODEL","same songs link. Not Downloading")
        }
    }

    fun setBooksList(xmlData: String){
        uiScope.launch {
            _booksList.value = withContext(Dispatchers.IO){ ParseXML.booksList(xmlData) }
        }
    }

    fun setMoviesXML(urlPath: String){
        if(urlPath != _moviesLink.value){
            uiScope.launch {
                _moviesXML.value = withContext(Dispatchers.IO) { DownloadXML.downloadXML(urlPath) }
            }
            _moviesLink.value = urlPath
        }else{
            Log.d("VIEWMODEL","same songs link. Not Downloading")
        }
    }

    fun setMoviesList(xmlData: String){
        uiScope.launch {
            _moviesList.value = withContext(Dispatchers.IO){ ParseXML.moviesList(xmlData) }
        }
    }
}