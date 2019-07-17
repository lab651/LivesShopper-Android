package com.liveshopper.feature.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.liveshopper.R
import com.liveshopper.feature.tasklist.TaskListFragment
import com.liveshopper.feature.taskmap.TaskMapFragment

class TabPagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> TaskMapFragment.newInstance()
        1 -> TaskListFragment.newInstance()
        else -> throw Exception("Tab for position $position not found")
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> context.getString(R.string.map)
        else -> context.getString(R.string.list)
    }
}
