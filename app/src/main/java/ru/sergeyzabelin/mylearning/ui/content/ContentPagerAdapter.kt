package ru.sergeyzabelin.mylearning.ui.content

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()
    private val fragmentIconList: MutableList<Int> = ArrayList()

    fun getTabTitle(position: Int): String {
        return fragmentTitleList[position]
    }

    fun getTabIcon(position: Int): Int {
        return fragmentIconList[position]
    }

    fun addFragment(fragment: Fragment, icon: Int) {
        fragmentList.add(fragment)
        fragmentIconList.add(icon)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}