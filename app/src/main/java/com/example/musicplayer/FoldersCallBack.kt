package com.example.musicplayer

interface FoldersCallBack {

    fun itemClicked(foldersModel: FoldersModel, position:Int)

    fun menuItemClicked(foldersModel: FoldersModel, position:Int)

    fun rename(foldersModel: FoldersModel, position:Int)

    fun delete(foldersModel: FoldersModel, position:Int)
}