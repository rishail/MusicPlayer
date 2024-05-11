package com.example.musicplayer

interface SongsByFolderCallBack {

    fun itemClicked(songsByFolderModel: SongsByFolderModel, position:Int)
    fun menuItemClicked(songsByFolderModel: SongsByFolderModel, position: Int)
    fun rename(songsByFolderModel: SongsByFolderModel,position: Int)

    fun delete(songsByFolderModel: SongsByFolderModel,position: Int)

    fun setRingTone(songsByFolderModel: SongsByFolderModel,position: Int)

    fun share(songsByFolderModel: SongsByFolderModel,position: Int)

}