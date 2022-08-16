package ru.sergeyzabelin.mylearning.ui.content

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContentPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val fragmentIcons: MutableList<Int> = ArrayList()

    fun getTabIcon(position: Int): Int {
        return fragmentIcons[position]
    }

    fun addFragment(fragment: Fragment, icon: Int) {
        fragments.add(fragment)
        fragmentIcons.add(icon)
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}