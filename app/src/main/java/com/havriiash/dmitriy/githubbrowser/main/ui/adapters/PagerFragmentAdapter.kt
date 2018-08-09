package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dagger.android.support.DaggerFragment

class PagerFragmentAdapter(
        fm: FragmentManager,
        private val fragments: List<DaggerFragment>,
        private val titles: List<String>
): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

}