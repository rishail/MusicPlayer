package com.example.musicplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicplayer.databinding.FragmentSongsByFolderBinding
import com.example.musicplayer.databinding.FragmentSongsListBinding

class SongsByFolderFragment : Fragment() {

    private lateinit var binding:FragmentSongsByFolderBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

     binding=FragmentSongsByFolderBinding.inflate(inflater, container, false)


        return binding.root
    }


}