package com.example.musicplayer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentFoldersListBinding.inflate(inflater, container, false)

        adapter= FoldersListAdapter(DashboardFragment.foldersList,this)

        binding.foldersListRecycler.adapter = adapter
        binding.foldersListRecycler.layoutManager = LinearLayoutManager(context)

        val spacingItems = RecyclerViewSpacingItems(95)
        binding.foldersListRecycler.addItemDecoration(spacingItems)


        return binding.root

    }

    override fun itemClicked(folders: Folders, position: Int) {

//         findNavController().navigate(R.id.action_dashboard_fragment_to_songsByFolderFragment)
    }

    override fun menuItemClicked(folders: Folders, position: Int) {

        Toast.makeText(context,"Menu Item is Clicked",Toast.LENGTH_SHORT).show()
    }

    override fun rename(folders: Folders, position: Int) {
    }

    override fun delete(folders: Folders, position: Int) {
    }


}