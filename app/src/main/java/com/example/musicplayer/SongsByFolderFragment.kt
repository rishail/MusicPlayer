package com.example.musicplayer

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.databinding.FragmentSongsByFolderBinding


class SongsByFolderFragment : Fragment(),SongsOptions {

    private lateinit var binding:FragmentSongsByFolderBinding
    private var adapter: SongsByFolderAdapter? = null


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

     binding=FragmentSongsByFolderBinding.inflate(inflater, container, false)

        adapter= SongsByFolderAdapter(DashboardFragment.songList,this,context!!)

        binding.songsbyfolderrecycler.adapter = adapter
        binding.songsbyfolderrecycler.layoutManager = LinearLayoutManager(context)

        val spacingItems = RecyclerViewSpacingItems(95)
        binding.songsbyfolderrecycler.addItemDecoration(spacingItems)

        getSongsByFolder(context!!)

        return binding.root
    }

    private fun getSongsByFolder(context: Context) {
        val tempAudioList: MutableList<SongsByFolder> = DashboardFragment.songsByFolders
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST
        )
        val c = context.contentResolver.query(uri,projection,MediaStore.Audio.Media.DATA + " like ? ",arrayOf("%utm%"),null)
        if (c != null) {
            while (c.moveToNext()) {

                val path = c.getString(0)
                val title = c.getString(1)
                val artist = c.getString(3)

                val audioModel = SongsByFolder(path,title,artist)

                audioModel.title
                audioModel.artist
                audioModel.path
                tempAudioList.add(audioModel)

                Log.d(Constants.TAG,audioModel.title)
            }
            c.close()
        }

    }

    override fun itemClicked(music: Music, position: Int) {
    }

    override fun menuItemClicked(music: Music, position: Int) {
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