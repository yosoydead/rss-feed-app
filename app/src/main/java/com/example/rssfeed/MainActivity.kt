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
import kotlinx.android.synthetic.main.activity_main.*

//this is the main activity of the app that hosts a navigation graph and swaps fragments
class MainActivity : AppCompatActivity(){
    private  lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //this helps me get rid of the back button shown on the app bar when navigating to other fragments
        val appBarConfig = AppBarConfiguration.Builder(
            R.id.musicFragment,
            R.id.booksFragment,
            R.id.moviesFragment).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)

        //bot_nav.setupWithNavController(navController)
        //this makes sure the navigation works and that if the same tab is clicked, the fragments is not redrawn
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
//        adapter = SongsAdapter(songs)
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

        //viewAdapter = SongsAdapter(songs)

        //viewAdapter = SongsAdapter(songs)

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

    //if i press back regardless the fragment im in, the app minimizes
    override fun onBackPressed() {
        //super.onBackPressed()
        finish()
    }

}
