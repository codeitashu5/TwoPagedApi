package com.ashupandey.twopageapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager,val list: MutableList<Fragment>) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    fun add(frag:Fragment){
        list.add(frag)
    }

    override fun getItem(position: Int): Fragment {
       return list[position]
    }

}