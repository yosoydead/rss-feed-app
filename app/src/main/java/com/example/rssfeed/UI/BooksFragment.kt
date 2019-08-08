package com.example.rssfeed.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.R
import com.example.rssfeed.RV_Adapter.RecyclerViewAdapter
import com.example.rssfeed.Util.GenerateUrl
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.apple_music_fragment.view.*

class BooksFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.books_fragment, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        //initializing the recycler view
//        recyclerView = view.music_recycler_view
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val link = GenerateUrl.generateATOM("tv-shows","top-tv-seasons", "25")

    }


    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        //Log.d("MUSIC", "enter $enter")
        //return super.onCreateAnimation(transit, enter, nextAnim)
        if(enter == true){
            return AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        }else{
            return AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
        }
    }


    //    override fun onDetach() {
//        super.onDetach()
//        Log.d("TVSHOWS", "detached")
//    }
//
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TVSHOWS", "destroyed")
    }

}