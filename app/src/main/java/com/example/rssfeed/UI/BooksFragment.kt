package com.example.rssfeed.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.R
import com.example.rssfeed.RV_Adapter.BooksAdapter
import com.example.rssfeed.RV_Adapter.SongsAdapter
import com.example.rssfeed.Util.GenerateUrl
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.books_fragment.view.*

class BooksFragment: Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BooksAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.books_fragment, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        //initializing the recycler view
        recyclerView = view.books_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BooksAdapter(viewModel.booksList.value!!)
        recyclerView.adapter = adapter

        val link = GenerateUrl.generateATOM("books","top-paid", "25")
        viewModel.setbooksXML(link)

        viewModel.booksXML.observe(this, Observer {
            viewModel.setBooksList(it)
            //Log.d("MUSIC FRAGM", "xml link: $it")
        })

        //if new xml data is parsed and the viewmodel songList changes its size
        //update the recycler view to have new data in it
        viewModel.booksList.observe(this, Observer {
            adapter.updateData(it)
        })
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