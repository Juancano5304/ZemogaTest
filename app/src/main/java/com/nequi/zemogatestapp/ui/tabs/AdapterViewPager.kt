package com.nequi.zemogatestapp.ui.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdapterViewPager(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager)
    {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitulo = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitulo[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitulo.add(title)
        }
}