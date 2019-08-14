package com.example.rssfeed.RV_Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.Model.Song
import com.example.rssfeed.R
import com.squareup.picasso.Picasso


class SongsAdapter(private var screenHeight: Float,
                   private var songList: List<Song>): RecyclerView.Adapter<SongsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view: View

        if(screenHeight > 400){
            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout_small, parent, false)
        }

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(songList.size > 0){
            return songList.size
        }else{
            return 1
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(songList.isEmpty()){
            holder.title.text = "N/A"
            holder.artist.text = "N/A"
        }else{
            holder.title.text = songList[position].title
            holder.artist.text = songList[position].artist
            holder.date.text = songList[position].published
            holder.imgUrl.text = songList[position].imgUrl
            holder.id.text = "${position +1}"

            Picasso.get().load(songList[position].imgUrl)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)
        }
    }

    fun getSong(position: Int): Song?{
        return if(songList.isNotEmpty()) songList[position] else null
    }

    fun updateData(newList: List<Song>){
        songList = newList
        notifyDataSetChanged()
        //Log.d("RECYCLERVIEW CHANGED","$newList")
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.song_title)
        var artist: TextView = view.findViewById(R.id.song_artist)
        var date: TextView = view.findViewById(R.id.song_date)
        var id: TextView = view.findViewById(R.id.numberId)
        var imgUrl: TextView = view.findViewById(R.id.imgUrl)
        var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    }
}