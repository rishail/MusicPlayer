package com.example.musicplayer

interface SongsOptions {

    fun itemClicked(music: Music,position:Int)
    fun menuItemClicked(music: Music,position: Int)
    fun rename(music: Music,position: Int)
    fun delete(music: Music,position: Int)
    fun setRingTone(music: Music,position: Int)
    fun share(music: Music,position: Int)

}