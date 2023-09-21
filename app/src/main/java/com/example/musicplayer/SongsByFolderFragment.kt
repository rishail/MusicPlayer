package com.example.musicplayer

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.musicplayer.databinding.FragmentSongsByFolderBinding

class SongsByFolderFragment : Fragment() {

    private lateinit var binding:FragmentSongsByFolderBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

     binding=FragmentSongsByFolderBinding.inflate(inflater, container, false)


        return binding.root
    }



}