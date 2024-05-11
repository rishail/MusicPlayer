package com.example.musicplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.databinding.SongsByFolderAdapterBinding

class SongsByFolderAdapter(

    private var songsByFolderAdapter: ArrayList<SongsByFolderModel>,
    private val callback: SongsByFolderFragment,
    private val context: Context,

    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val  binding = SongsByFolderAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return songsByFolderAdapter.size
    }

    class ItemViewHolder(val binding: SongsByFolderAdapterBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder

        val currentItem = songsByFolderAdapter[position]


        holder.binding.songName.text=currentItem.title
        holder.binding.artistName.text=currentItem.artist


        Glide.with(context)
            .load(songsByFolderAdapter[position].uri).placeholder(R.drawable.music_art).error(R.drawable.music_art)
            .into(holder.binding.songThumbnail)

        holder.binding.root.setOnClickListener()
        {

            callback.itemClicked(songsByFolderAdapter[position],position)
        }

        holder.binding.songMenuOptions.setOnClickListener(){

            callback.menuItemClicked(songsByFolderAdapter[position],position)

        }

    }

    fun updateData(newData: ArrayList<SongsByFolderModel>) {
        songsByFolderAdapter.clear()
        songsByFolderAdapter.addAll(newData)
        notifyDataSetChanged()
    }



}