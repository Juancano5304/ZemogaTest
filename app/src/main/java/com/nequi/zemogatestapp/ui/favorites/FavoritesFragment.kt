package com.nequi.zemogatestapp.ui.favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.databinding.FavoriteElementBinding
import com.nequi.zemogatestapp.databinding.FavoritesFragmentBinding
import com.nequi.zemogatestapp.databinding.ListFragmentBinding
import com.nequi.zemogatestapp.ui.description.DescriptionViewModel
import com.nequi.zemogatestapp.ui.list.ListAdapter
import com.nequi.zemogatestapp.ui.list.ListViewModel
import com.nequi.zemogatestapp.ui.list.PostListener
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.list_fragment.*

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var adapter: FavoritesAdapter
    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        viewModel.allFavorites.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoritesAdapter()
        favoritesRecyclerView = favorites_recycler
        favoritesRecyclerView.adapter = adapter
        favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}