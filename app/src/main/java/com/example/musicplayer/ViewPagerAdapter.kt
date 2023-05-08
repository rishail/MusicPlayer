package com.example.musicplayer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity?) : FragmentStateAdapter(fragmentActivity!!) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            SongsListFragment()
        } else {
            FoldersListFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}