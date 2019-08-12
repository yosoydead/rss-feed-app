package com.example.rssfeed.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.R
import com.example.rssfeed.RV_Adapter.SongsAdapter
import com.example.rssfeed.Util.GenerateUrl
import com.example.rssfeed.Util.RecyclerItemClickListener
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.apple_music_fragment.view.*

class MusicFragment: Fragment(),RecyclerItemClickListener.OnRecyclerClickListener {


    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SongsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MUSIC", "created")
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        //Log.d("MUSIC", "enter $enter")
        //return super.onCreateAnimation(transit, enter, nextAnim)
        if(enter == true){
            return AnimationUtils.loadAnimation(context, R.anim.fade_in)
        }else{
            return AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
        }
    }

    /*this method is called to draw the ui of the fragment
      it is not recommended to use findViewById method or similar stuff because it may result in an error
            because the view could be incomplete
    */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.apple_music_fragment, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        //initializing the recycler view
        recyclerView = view.music_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        return view
    }

    //this method is called right after onCreateView so it's a good place to setup listeners and stuff
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SongsAdapter(viewModel.songsList.value!!)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context!!, recyclerView, this))
        recyclerView.adapter = adapter

        val link = GenerateUrl.generateATOM("apple-music","coming-soon", "25")
        viewModel.setSongsXML(link)

        viewModel.songsXML.observe(this, Observer {
            viewModel.setSongList(it)
        })

        //if new xml data is parsed and the viewmodel songList changes its size
        //update the recycler view to have new data in it
        viewModel.songsList.observe(this, Observer {
            adapter.updateData(it)
        })
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d("MUSIC","onItemClick starts")
        Toast.makeText(context, "Normal tap at position $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d("MUSIC", "onItemLongClick starts")

        val song = adapter.getSong(position)

        //create an intent to navigate to song details with context and class
        val intent = Intent(context, MusicDetailsActivity::class.java)
        intent.putExtra("TITLE","${song?.title}")
        intent.putExtra("ARTIST", "${song?.artist}")
        startActivity(intent)
        //Toast.makeText(context, "Long tap at position $position", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "Long tap on ${song?.artist}", Toast.LENGTH_SHORT).show()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("MUSIC","destroyed")
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        Log.d("MUSIC", "detached")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.d("MUSIC","view destroyed")
//    }

}