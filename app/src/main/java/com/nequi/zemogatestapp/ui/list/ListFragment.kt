package com.nequi.zemogatestapp.ui.list

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.databinding.ListFragmentBinding
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private lateinit var activityContext: Context
    private lateinit var viewModel: ListViewModel
    private lateinit var listRecyclerview: RecyclerView
    private lateinit var adapter: ListAdapter
    private lateinit var binding: ListFragmentBinding
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var itemTouchCallback: ItemTouchHelper.SimpleCallback

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        viewModel.allPosts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        adapter = ListAdapter(PostListener{
            postId -> viewModel.postClicked(postId)
        })
        listRecyclerview = list_recycler
        listRecyclerview.adapter = adapter
        listRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.navigateToDescription.observe(viewLifecycleOwner, Observer {
            postId -> postId?.let {
                viewModel.updateRead(postId)
                viewModel.callServiceGetUser(requireContext(), postId)
                viewModel.callServiceGetComments(requireContext(), postId)
                Log.i("list", viewModel.allPosts.value!![postId].toString())
                val bundle = Bundle()
                bundle.putString("post", GsonBuilder().create().toJson(viewModel.allPosts.value!![postId]))
                this.findNavController().navigate(R.id.action_tabsFragment_to_descriptionFragment, bundle)
                viewModel.descriptionNavigated()
            }
        })

        itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if(direction == ItemTouchHelper.LEFT) {
                    viewModel.delete(adapter.getPosition(viewHolder.adapterPosition))
                    Toast.makeText(activityContext, "Post borrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(listRecyclerview)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

}