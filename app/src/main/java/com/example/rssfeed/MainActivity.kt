package com.example.rssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var viewJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewJob)
    private lateinit var viewModel: MainViewModel

    private var songs = arrayListOf<Song>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    val link = "https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/10/non-explicit.rss"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MAINACTIVITY", "started app")
        viewManager = LinearLayoutManager(this)
        songs.add(Song("blabla", "jbfsdf","19151602"))
        songs.add(Song("n kfwefw", "bfldsfssd", "616216"))
        viewAdapter = RecyclerViewAdapter(songs)
        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply{
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

//        viewModel.data.observe(this, Observer<String> {
//            //textBox.text = viewModel.data.value
//        })

        viewModel.show.observe(this, Observer {
            loadingPanel.visibility = viewModel.show.value ?: View.VISIBLE
        })

    }

}
