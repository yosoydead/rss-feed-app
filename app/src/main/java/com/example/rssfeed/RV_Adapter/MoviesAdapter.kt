package com.example.rssfeed.RV_Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.Model.Book
import com.example.rssfeed.Model.Movie
import com.example.rssfeed.R
import com.squareup.picasso.Picasso

class MoviesAdapter (private var moviesList: List<Movie>): RecyclerView.Adapter<MoviesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(moviesList.size > 0){
            return moviesList.size
        }else{
            return 1
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(moviesList.isEmpty()){
            holder.title.text = "N/A"
            holder.artist.text = "N/A"
        }else{
            holder.title.text = moviesList[position].title
            holder.artist.text = moviesList[position].director
            holder.date.text = moviesList[position].published
            holder.imgUrl.text = moviesList[position].imgUrl
            holder.id.text = "${position +1}"

            Picasso.get().load(moviesList[position].imgUrl)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)
        }
    }

    fun getMovie(position: Int): Movie?{
        return if(moviesList.isNotEmpty()) moviesList[position] else null
    }

    fun updateData(newList: List<Movie>){
        moviesList = newList
        notifyDataSetChanged()
        //Log.d("RECYCLERVIEW CHANGED","$newList")
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.song_title)
        var artist: TextView = view.findViewById(R.id.song_artist)
        var date: TextView = view.findViewById(R.id.song_date)
        var id: TextView = view.findViewById(R.id.numberId)
        var imgUrl: TextView = view.findViewById(R.id.imgUrl)
        var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    }
}