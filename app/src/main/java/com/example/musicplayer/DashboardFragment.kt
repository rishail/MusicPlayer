package com.example.musicplayer

import android.Manifest
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
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.musicplayer.databinding.FragmentDashboardBinding
import com.example.musicplayer.databinding.SongListAdapterBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle

    companion object{

        var songList = ArrayList<MusicModel>()
        var foldersList=ArrayList<FoldersModel>()
        var SongsByFolderList=ArrayList<SongsByFolderModel>()
        val sortList = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.TITLE + " ASC",
        )
        var sortValue: Int = 0
    }

    private val songListInstance=SongsListFragment()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding=FragmentDashboardBinding.inflate(inflater,container,false)


        viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = viewPagerAdapter

        drawerLayout = binding.drawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(activity, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
                tab: TabLayout.Tab, position: Int ->
            if (position == 0) {
                tab.setText(R.string.tab_layout_label_songs)
            } else {
                tab.setText(R.string.tab_layout_folders_name)
            }
        }.attach()


        binding.customDrawerIcon.setOnClickListener() {

            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {

                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            else {
                drawerLayout.openDrawer(GravityCompat.START)
            }


            val header: View = binding.navHeaderDrawer.getHeaderView(0)


            header.findViewById<Button>(R.id.btn_remove_ads).setOnClickListener {

               showBottomSheetPremium()

            }


            header.findViewById<Button>(R.id.btn_our_apps).setOnClickListener {

                Toast.makeText(context, "Our Apps(Ad) is pressed", Toast.LENGTH_SHORT).show()
            }

            header.findViewById<Button>(R.id.btn_give_rating).setOnClickListener {

                Toast.makeText(context, "Give us rating is pressed", Toast.LENGTH_SHORT).show()
            }

            header.findViewById<Button>(R.id.btn_feed_back).setOnClickListener {

                Toast.makeText(context, "Your FeedBack is pressed", Toast.LENGTH_SHORT).show()
            }

            header.findViewById<Button>(R.id.btn_terms_privacy).setOnClickListener {

                Toast.makeText(context, "Terms and privacy is pressed", Toast.LENGTH_SHORT).show()
            }

            header.findViewById<Button>(R.id.btn_app_update).setOnClickListener {

                Toast.makeText(requireContext(), "Update App is pressed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.premiumIcon.setOnClickListener(){

            showBottomSheetPremium()
        }

        permissionRequestLauncher.launch(permissionsHandler())


        return binding.root

    }


    private fun showBottomSheetPremium(){

        val bottomSheet = PremiumScreenBottomSheet()

        bottomSheet.show(requireActivity().supportFragmentManager,bottomSheet.tag)
    }

    private fun permissionsHandler(): Array<String> {
        val p: Array<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            storagePermissions13
        } else {
            storagePermissions
        }
        return p
    }

    private var storagePermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    var storagePermissions13 = arrayOf(Manifest.permission.READ_MEDIA_AUDIO)

    @RequiresApi(Build.VERSION_CODES.Q)
    var permissionRequestLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            var allAreGranted = true
            for (b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if (allAreGranted) {
               songListInstance.getMusicList()
            } else {
                Toast.makeText(context, "Permission is denied", Toast.LENGTH_SHORT).show()
            }
        }

}




