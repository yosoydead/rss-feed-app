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
import com.example.rssfeed.RV_Adapter.SongsAdapter
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.movies_fragment.view.*

class MoviesFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SongsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        //initializing the recycler view
        recyclerView = view.movies_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("MOVIES","destroyed")
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
}