package com.example.musicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.databinding.SongListAdapterBinding

class SongsListAdapter(

    private var songsListAdapter: ArrayList<Music>,
    private val callBack: SongsOptions,

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = SongListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return songsListAdapter.size
    }

    class ItemViewHolder(val binding: SongListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder

        holder.binding.songName.text=songsListAdapter[position].title
        holder.binding.artistName.text=songsListAdapter[position].artist



        holder.binding.root.setOnClickListener()
        {

            callBack.itemClicked(songsListAdapter[position],position)
        }

        holder.binding.songMenuOptions.setOnClickListener(){

            callBack.menuItemClicked(songsListAdapter[position],position)

        }



    }


}