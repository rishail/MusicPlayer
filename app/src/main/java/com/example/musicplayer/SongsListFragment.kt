package com.example.musicplayer

import android.annotation.SuppressLint
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.musicplayer.databinding.FragmentSongsListBinding
import com.example.musicplayer.databinding.SongListAdapterBinding


class SongsListFragment : Fragment(),SongsCallBack {

 private lateinit var binding: FragmentSongsListBinding
    private var adapter: SongsListAdapter? = null

    override fun onResume() {
        super.onResume()
        binding.songListRecycler.adapter = SongsListAdapter(DashboardFragment.songList,this,requireContext())
    }

    private val songListAdapterBinding by lazy {
        SongListAdapterBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentSongsListBinding.inflate(inflater, container, false)

        adapter= SongsListAdapter(DashboardFragment.songList,this,requireContext())

        binding.songListRecycler.adapter = adapter
        binding.songListRecycler.layoutManager = LinearLayoutManager(context)

        val spacingItems = RecyclerViewSpacingItems(95)
        binding.songListRecycler.addItemDecoration(spacingItems)

        getMusicList()

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range")
    fun getMusicList() {

        val cr = context?.contentResolver
        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"
        val cursor: Cursor? = cr?.query(collection, null, selection, null, sortOrder)
        val count: Int

        if (cursor != null) {
            count = cursor.count
            if (count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val title =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val albumId =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))


                    val sArt = Uri.parse("content://media/external/audio/albumart")
                    val uri = ContentUris.withAppendedId(sArt, albumId)


                    val musicModel = MusicModel(id, title, artist, albumId, uri)
                    DashboardFragment.songList.add(musicModel)

                    Glide.with(this).load(uri).placeholder(R.drawable.music_art)
                        .error(R.drawable.music_art).into(songListAdapterBinding.songThumbnail)
                }
            }
            cursor.close()
        }

    }

    override fun itemClicked(musicModel: MusicModel, position: Int) {


    }

    override fun menuItemClicked(musicModel: MusicModel, position: Int) {

        Toast.makeText(context,"Menu Item is Clicked",Toast.LENGTH_SHORT).show()
    }

    override fun rename(musicModel: MusicModel, position: Int) {

    }

    override fun delete(musicModel: MusicModel, position: Int) {

    }

    override fun setRingTone(musicModel: MusicModel, position: Int) {

    }

    override fun share(musicModel: MusicModel, position: Int) {


    }



    }


