package com.example.musicplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.databinding.SongListAdapterBinding


class SongsListAdapter(

    private var songsListAdapter: ArrayList<Music>,
    private val options: SongsListFragment,
    private val context:Context,


    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     private var selectedItem = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

       val  binding = SongListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int {

        return songsListAdapter.size
    }

  inner class ItemViewHolder(val binding: SongListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Music, isSelected:Boolean){
            itemView.isSelected=isSelected
        }

        fun selectItem(position: Int){

            selectedItem=position
            notifyItemChanged(position)
        }

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder

        holder.binding.songName.text=songsListAdapter[position].title
        holder.binding.artistName.text=songsListAdapter[position].artist

        holder.bind(songsListAdapter[position], position == selectedItem)



        Glide.with(context)
            .load(songsListAdapter[position].uri).placeholder(R.drawable.music_art).error(R.drawable.music_art)
            .into(holder.binding.songThumbnail)

        holder.binding.root.setOnClickListener()
        {

            options.itemClicked(songsListAdapter[position],position)
        }

        holder.binding.songMenuOptions.setOnClickListener(){

            options.menuItemClicked(songsListAdapter[position],position)

        }

    }



}



