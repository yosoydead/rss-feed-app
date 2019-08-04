package com.example.rssfeed.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.rssfeed.R

class MusicFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.apple_music_fragment, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MUSIC","destroyed")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MUSIC", "detached")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MUSIC","view destroyed")
    }

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
}