package com.example.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.databinding.FragmentSongsListBinding

class SongsListFragment : Fragment(),SongsOptions {

 private lateinit var binding: FragmentSongsListBinding
    private var adapter: SongsListAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        binding = FragmentSongsListBinding.inflate(inflater, container, false)

        adapter= SongsListAdapter(DashboardFragment.songList,this)
        binding.songListRecycler.layoutManager = LinearLayoutManager(context)
        binding.songListRecycler.adapter = adapter

        val spacingItems = RecyclerViewSpacingItems(95)
        binding.songListRecycler.addItemDecoration(spacingItems)

        return binding.root
    }

    override fun itemClicked(music: Music, position: Int) {

        Toast.makeText(context,"Item is Clicked",Toast.LENGTH_SHORT).show()

    }

    override fun menuItemClicked(music: Music, position: Int) {

        Toast.makeText(context,"Menu Item is Clicked",Toast.LENGTH_SHORT).show()
    }

    override fun rename(music: Music, position: Int) {

    }

    override fun delete(music: Music, position: Int) {

    }

    override fun setRingTone(music: Music, position: Int) {

    }

    override fun share(music: Music, position: Int) {
    }


}