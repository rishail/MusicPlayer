package com.example.musicplayer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.DashboardFragment.Companion.songList
import com.example.musicplayer.databinding.SongListAdapterBinding


class SongsListAdapter(
    private var songsListAdapter: ArrayList<MusicModel>,
    private val callback: SongsListFragment,
    private val context: Context,

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition=-1

    inner class ViewHolder(val binding: SongListAdapterBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = SongListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return songsListAdapter.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,@SuppressLint("RecyclerView") position: Int) {
        holder as ViewHolder

        if (selectedPosition==position){
            holder.binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.item_selected_recycler))
            holder.binding.songAnimImgview.visibility=View.VISIBLE
            holder.binding.songName.setTextColor(ContextCompat.getColor(context,R.color.item_name_highlighter))
        }

        else{
            holder.binding.root.setBackgroundColor(Color.TRANSPARENT)
            holder.binding.songAnimImgview.visibility=View.INVISIBLE
            holder.binding.songName.setTextColor(ContextCompat.getColor(context,R.color.white))
        }


        holder.binding.songName.text = songsListAdapter[position].title
        holder.binding.artistName.text = songsListAdapter[position].artist

        Glide.with(context)
            .load(songsListAdapter[position].uri).placeholder(R.drawable.music_art)
            .error(R.drawable.music_art)
            .into(holder.binding.songThumbnail)


            holder.binding.root.setOnClickListener()
            {
                callback.itemClicked(songsListAdapter[position], position)
                selectedPosition=position
                notifyDataSetChanged()
            }

            holder.binding.songMenuOptions.setOnClickListener() {

                callback.menuItemClicked(songsListAdapter[position], position)

            }

        }

    fun updateList(newList: ArrayList<MusicModel>) {
        songList.clear()
        songList.addAll(newList)
        notifyDataSetChanged()
    }


    }




