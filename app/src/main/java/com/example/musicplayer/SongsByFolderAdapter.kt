package com.example.musicplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.databinding.SongsByFolderAdapterBinding

class SongsByFolderAdapter(

    private var songsByFolderAdapter: ArrayList<Music>,
    private val options: SongsByFolderFragment,
    private val context: Context,

    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val  binding = SongsByFolderAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int {

        return songsByFolderAdapter.size
    }

    class ItemViewHolder(val binding: SongsByFolderAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder

        holder.binding.songName.text=songsByFolderAdapter[position].title
        holder.binding.artistName.text=songsByFolderAdapter[position].artist


        Glide.with(context)
            .load(songsByFolderAdapter[position].uri).placeholder(R.drawable.music_art).error(R.drawable.music_art)
            .into(holder.binding.songThumbnail)

        holder.binding.root.setOnClickListener()
        {

            options.itemClicked(songsByFolderAdapter[position],position)
        }

        holder.binding.songMenuOptions.setOnClickListener(){

            options.menuItemClicked(songsByFolderAdapter[position],position)

        }

    }


}