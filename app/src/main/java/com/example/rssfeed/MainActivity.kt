package com.example.rssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
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
    private  lateinit var navController: NavController

//    private var songs = arrayListOf<Song>()
//
////    private lateinit var recyclerView: RecyclerView
////    private lateinit var viewAdapter: RecyclerView.Adapter<*>
////    private lateinit var viewManager: RecyclerView.LayoutManager
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: RecyclerViewAdapter
//
//    val link = "https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/10/non-explicit.rss"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bot_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)



//        recyclerView = findViewById(R.id.my_recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//
//        adapter = RecyclerViewAdapter(songs)
//        recyclerView.adapter = adapter
//
//        Log.d("MAINACTIVITY", "started app")
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//
//        //viewManager = LinearLayoutManager(this)
////        songs.add(Song("blabla", "jbfsdf","19151602"))
////        songs.add(Song("n kfwefw", "bfldsfssd", "616216"))
//
//        viewModel.songs.observe(this, Observer {
//            //Log.d("View songs","$songs")
//            songs = viewModel.songs.value!!
//            adapter.updateData(songs)
//            //Log.d("VIEW SONGS", "$songs")
//        })
//
//        viewModel.show.observe(this, Observer {
//            loadingPanel.visibility = viewModel.show.value ?: View.VISIBLE
//        })
//
//        var currentID = choice_group.checkedChipId
////        Log.d("VIEW", "currentID: $currentID")
//
//        choice_group.setOnCheckedChangeListener{ group, checkedId ->
//
//            if(checkedId != -1){
//                //Toast.makeText(this, "clicked on ${checkedId}",Toast.LENGTH_SHORT).show()
//
//                currentID = checkedId
//                val chip = group.findViewById<Chip>(currentID)
//
//                Toast.makeText(this,"clicked on: ${chip.text}",Toast.LENGTH_SHORT).show()
//
//            }else{
//                val chip = group.findViewById<Chip>(currentID)
////
////                Toast.makeText(this,"clicked again on: ${chip.text}",Toast.LENGTH_SHORT).show()
//                chip.isChecked = true
//            }
////            val chip = group.findViewById<Chip>(group.checkedChipId)
////            if(chip != null){
////                //Toast.makeText(this, "clicked on ${chip.text}",Toast.LENGTH_SHORT).show()
////                Toast.makeText(this, "clicked on ${group.checkedChipId}",Toast.LENGTH_SHORT).show()
////            }else{
////                //Toast.makeText(this, "clicked on null",Toast.LENGTH_SHORT).show()
////                Toast.makeText(this, "clicked on ${group.checkedChipId}",Toast.LENGTH_SHORT).show()
////
////            }
//            //Toast.makeText(this, "clicked on ${currentID}",Toast.LENGTH_SHORT).show()
//
//        }

        //viewAdapter = RecyclerViewAdapter(songs)

        //viewAdapter = RecyclerViewAdapter(songs)

//        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply{
//            setHasFixedSize(true)
//
//            // use a linear layout manager
//            layoutManager = viewManager
//
//            // specify an viewAdapter (see also next example)
//            adapter = viewAdapter
//        }




//        viewModel.data.observe(this, Observer<String> {
//            //textBox.text = viewModel.data.value
//        })

    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, null)
//    }

}
