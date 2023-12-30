package com.example.musicplayer

interface SongsCallBack {

    fun itemClicked(musicModel: MusicModel, position:Int)
    fun menuItemClicked(musicModel: MusicModel, position: Int)
    fun rename(musicModel: MusicModel, position: Int)
    fun delete(musicModel: MusicModel, position: Int)
    fun setRingTone(musicModel: MusicModel, position: Int)
    fun share(musicModel: MusicModel, position: Int)

}