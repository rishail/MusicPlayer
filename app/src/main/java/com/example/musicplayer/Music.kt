package com.example.musicplayer

import android.net.Uri


data class Music(val id: String, val title:String, val artist:String, val albumId: Long,val uri:Uri)
