package com.nequi.zemogatestapp.ui.tabs

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.ui.favorites.FavoritesFragment
import com.nequi.zemogatestapp.ui.list.ListFragment
import kotlinx.android.synthetic.main.tabs_fragment.*

class TabsFragment : Fragment() {

    companion object {
        fun newInstance() = TabsFragment()
    }

    private lateinit var viewModel: TabsViewModel
    private lateinit var adapter: AdapterViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tabs_fragment, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        adapter = AdapterViewPager(requireFragmentManager())
        adapter.addFragment(ListFragment(), "ALL")
        adapter.addFragment(FavoritesFragment(), "FAVORITES")
        viewPager.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TabsViewModel::class.java)
        setupViewPager(view_pager)
        tab_layout.setupWithViewPager(view_pager)
    }
}