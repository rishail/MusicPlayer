package com.example.musicplayer

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.musicplayer.Constants.TAG
import com.example.musicplayer.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.concurrent.TimeUnit


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle

    companion object{

        var songList = ArrayList<Music>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

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

                PremiumScreenBottomSheet()

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

        getMusicList()

        return binding.root
    }

    private fun showBottomSheetPremium(){

        val bottomSheet = PremiumScreenBottomSheet()

        bottomSheet.show(activity!!.supportFragmentManager,bottomSheet.tag)
    }

    private fun getMusicList(){

        val contentResolver =context?.contentResolver
        val selection = "${MediaStore.Audio.Media.TITLE} >= ?"
        val selectionArgs = arrayOf(
            TimeUnit.MILLISECONDS.convert(2, TimeUnit.SECONDS).toString())
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            }
            else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

        val cursor = contentResolver?.query(collection, null, selection, selectionArgs, sortOrder)
        if (cursor != null && cursor.count > 0 ) {

            val id = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val title =cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artist=cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

            while (cursor.moveToNext()) {

                val id = cursor.getInt(id)
                val title=cursor.getString(title)
                val artist=cursor.getString(artist)

                val music = Music(id,title,artist)
                songList.add(music)

                Log.d(TAG, "music list$music")

            }
            cursor.close()
        }
    }

}