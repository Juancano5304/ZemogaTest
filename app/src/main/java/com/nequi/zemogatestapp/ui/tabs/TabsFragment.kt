package com.nequi.zemogatestapp.ui.tabs

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.nequi.zemogatestapp.MainActivity
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
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.tabs_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.reload) {
            return false
        }
        if(id == R.id.favorite) {
            return true
        }
        return false
    }

    private fun setupViewPager(viewPager: ViewPager) {
        adapter = AdapterViewPager(childFragmentManager)
        adapter.addFragment(ListFragment(), "ALL")
        adapter.addFragment(FavoritesFragment(), "FAVORITES")
        viewPager.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TabsViewModel::class.java)
        setupViewPager(view_pager)
        tab_layout.setupWithViewPager(view_pager)
        activity?.title ="Posts"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}