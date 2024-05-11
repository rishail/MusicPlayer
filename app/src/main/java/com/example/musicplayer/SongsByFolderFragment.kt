package com.example.musicplayer

import android.annotation.SuppressLint
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.databinding.FragmentSongsByFolderBinding
import java.io.File

class SongsByFolderFragment : Fragment(),SongsByFolderCallBack {

    private lateinit var binding:FragmentSongsByFolderBinding
    private var adapter: SongsByFolderAdapter? = null

    private val args:SongsByFolderFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

     binding=FragmentSongsByFolderBinding.inflate(inflater, container, false)

        adapter= SongsByFolderAdapter(ArrayList(),this,requireContext())

        binding.songsbyfolderrecycler.adapter = adapter
        binding.songsbyfolderrecycler.layoutManager = LinearLayoutManager(context)

        val spacingItems = RecyclerViewSpacingItems(95)
        binding.songsbyfolderrecycler.addItemDecoration(spacingItems)

              val position=args

               DashboardFragment.SongsByFolderList=getSongsByFolder(DashboardFragment.foldersList[position.data].id)

               adapter?.updateData(DashboardFragment.SongsByFolderList)

        return binding.root
    }

    @SuppressLint("InlinedApi", "Recycle", "Range")
    private fun getSongsByFolder(folderId: String): ArrayList<SongsByFolderModel>{
        val tempList = ArrayList<SongsByFolderModel>()
        val selection = MediaStore.Audio.Media.BUCKET_ID + " like? "
        val projection = arrayOf(
            MediaStore.Audio.Media._ID, 
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.BUCKET_ID,
            MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Audio.Media.DATA,
          )
        val cursor = requireActivity().contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, arrayOf(folderId),
            DashboardFragment.sortList[DashboardFragment.sortValue])
        if(cursor != null)
            if(cursor.moveToNext())
                do {
                    val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                    val foldersName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.BUCKET_DISPLAY_NAME))
                    val path= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))

                    val sArt = Uri.parse("content://media/external/audio/albumart")
                    val uri = ContentUris.withAppendedId(sArt,albumId)

                    try {
                        val file = File(path)
                        val songsByFolderModel=SongsByFolderModel(id,title,artist,albumId,uri,foldersName)

                        if(file.exists()) tempList.add(songsByFolderModel)

                    }catch (_:Exception){}
                }while (cursor.moveToNext())
        cursor?.close()
        return tempList
    }


    override fun itemClicked(songsByFolderModel: SongsByFolderModel, position: Int) {
    }

    override fun menuItemClicked(songsByFolderModel: SongsByFolderModel, position: Int) {
    }

    override fun rename(songsByFolderModel: SongsByFolderModel, position: Int) {
    }

    override fun delete(songsByFolderModel: SongsByFolderModel, position: Int) {
    }

    override fun setRingTone(songsByFolderModel: SongsByFolderModel, position: Int) {
    }

    override fun share(songsByFolderModel: SongsByFolderModel, position: Int) {
    }

}