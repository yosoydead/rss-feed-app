package com.example.rssfeed.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.rssfeed.R

class TvShowsFragment: Fragment() {

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d("TVSHOWS", "attached")
//    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
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