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
import com.example.rssfeed.RV_Adapter.MoviesAdapter
import com.example.rssfeed.Util.GenerateUrl
import com.example.rssfeed.Util.RecyclerItemClickListener
import com.example.rssfeed.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.movies_fragment.view.*

class MoviesFragment: Fragment(), RecyclerItemClickListener.OnRecyclerClickListener  {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        //initializing the recycler view
        recyclerView = view.movies_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(viewModel.moviesList.value!!)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context!!, recyclerView, this))
        recyclerView.adapter = adapter

        val link = GenerateUrl.generateATOM("movies","top-movies", "25")

        viewModel.setMoviesXML(link)

        viewModel.moviesXML.observe(this, Observer {
            viewModel.setMoviesList(it)
        })

        //if new xml data is parsed and the viewmodel songList changes its size
        //update the recycler view to have new data in it
        viewModel.moviesList.observe(this, Observer {
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

    override fun onItemClick(view: View, position: Int) {
        Log.d("MUSIC","onItemClick starts")
        Toast.makeText(context, "Normal tap at position $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d("MUSIC", "onItemLongClick starts")
        val movie = adapter.getMovie(position)
        //Toast.makeText(context, "Long tap at position $position", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "Long tap on ${movie?.title}", Toast.LENGTH_SHORT).show()    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MOVIES","destroyed")
    }
}