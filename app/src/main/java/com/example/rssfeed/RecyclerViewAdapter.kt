package com.example.rssfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerViewAdapter(private var songList: List<Song>): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size ?: 1
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(songList.isEmpty()){
            holder.title.text = "N/A"
            holder.artist.text = "N/A"
        }else{
            holder.title.text = songList[position].title
            holder.artist.text = songList[position].artist
            holder.date.text = songList[position].published
        }
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.song_title)
        var artist: TextView = view.findViewById(R.id.song_artist)
        var date: TextView = view.findViewById(R.id.song_date)
    }
}