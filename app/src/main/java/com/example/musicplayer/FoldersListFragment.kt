package com.example.musicplayer


import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.databinding.FragmentFoldersListBinding


class FoldersListFragment : Fragment(),FoldersOptions {

    private lateinit var binding: FragmentFoldersListBinding
    private var adapter: FoldersListAdapter? = null


    override fun onResume() {
        super.onResume()
        binding.foldersListRecycler.adapter = FoldersListAdapter(DashboardFragment.foldersList,this)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoldersListBinding.inflate(inflater, container, false)


        adapter= FoldersListAdapter(DashboardFragment.foldersList,this)


        binding.foldersListRecycler.adapter = adapter
        binding.foldersListRecycler.layoutManager = LinearLayoutManager(context)

        val spacingItems = RecyclerViewSpacingItems(95)
        binding.foldersListRecycler.addItemDecoration(spacingItems)

        getFoldersList()


        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range")
    fun getFoldersList() {

        val tempFoldersList=ArrayList<String>()

        val cr = context?.contentResolver
        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection =  MediaStore.Audio.Media.IS_MUSIC + "!= 0"
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"
        val cursor: Cursor? = cr?.query(collection, null, selection, null, sortOrder)
        val count: Int

        if (cursor != null) {
            count = cursor.count
            if (count > 0) {
                while (cursor.moveToNext()) {

                    val foldersId= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.BUCKET_ID))
                    val foldersName= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.BUCKET_DISPLAY_NAME))

                    if (!tempFoldersList.contains(foldersName)){
                        tempFoldersList.add(foldersName)
                        val folders=Folders(foldersId,foldersName)
                        DashboardFragment.foldersList.add(folders)
//                        Log.d(Constants.TAG,"folders list$folders")
                    }
                }

            }
            cursor.close()
        }

    }



    override fun itemClicked(folders: Folders, position: Int) {

         findNavController().navigate(R.id.action_dashboard_fragment_to_songsByFolderFragment)

    }

    override fun menuItemClicked(folders: Folders, position: Int) {

        Toast.makeText(context,"Menu Item is Clicked",Toast.LENGTH_SHORT).show()
    }

    override fun rename(folders: Folders, position: Int) {
    }

    override fun delete(folders: Folders, position: Int) {
    }


}