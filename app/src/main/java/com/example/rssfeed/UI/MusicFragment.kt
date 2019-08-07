package com.example.rssfeed.UI

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.R
import com.example.rssfeed.RecyclerViewAdapter
import com.example.rssfeed.Util.GenerateUrl
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.apple_music_fragment.view.*

class MusicFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

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

        adapter = RecyclerViewAdapter(viewModel.songList.value!!)
        recyclerView.adapter = adapter

        val link = GenerateUrl.generateATOM("apple-music","coming-soon", "25")
        viewModel.setXML(link)

//        view.button.setOnClickListener{
//            val link = GenerateUrl.generateATOM("apple-music","coming-soon", "25")
//            Log.d("MUSIC FRAGMENT", "$link")
//            viewModel.setXML(link)
//            Toast.makeText(context, "Clicked on button", Toast.LENGTH_SHORT).show()
//            Log.d("MUSIC FRAGMENT", "song list: ${viewModel.songList.value}")
//        }

        viewModel.songXML.observe(this, Observer {
            viewModel.setSongList(it)
        })

        viewModel.songList.observe(this, Observer {
            adapter.updateData(it)
        })
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