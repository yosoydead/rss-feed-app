package com.example.rssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.example.rssfeed.Util.GenerateUrl
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//this is the main activity of the app that hosts a navigation graph and swaps fragments
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


        //bot_nav.setupWithNavController(navController)


        //this helps me get rid of the back button shown on the app bar when navigating to other fragments
        val appBarConfig = AppBarConfiguration.Builder(
            R.id.musicFragment,
            R.id.tvShowsFragment,
            R.id.moviesFragment).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)

        val url = GenerateUrl.generateATOM("books", "top-free", "10")
        Log.d("MAIN", url)


        var currentTab = bot_nav.selectedItemId
        bot_nav.setOnNavigationItemSelectedListener{ item ->
            Log.d("MAIN", "clicked on ${item.title}")
            if(item.itemId == currentTab){
                //Log.d("MAIN", "already in tab ${item.title}")
                Toast.makeText(this,"already on tab $item", Toast.LENGTH_SHORT).show()
                true
            }else{
                currentTab = item.itemId
                onNavDestinationSelected(item, navController)
            }
        }




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

    override fun onBackPressed() {
        //super.onBackPressed()
        finish()
    }

}
