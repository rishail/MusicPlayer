package com.example.musicplayer

interface FoldersOptions {

    fun itemClicked(folders: Folders, position:Int)

    fun menuItemClicked(folders: Folders, position:Int)

    fun rename(folders: Folders, position:Int)

    fun delete(folders: Folders, position:Int)
}