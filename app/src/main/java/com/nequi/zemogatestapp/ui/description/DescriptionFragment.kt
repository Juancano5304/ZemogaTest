package com.nequi.zemogatestapp.ui.description

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.databinding.DescriptionFragmentBinding
import com.nequi.zemogatestapp.databinding.DescriptionFragmentBindingImpl
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.repository.User
import com.nequi.zemogatestapp.ui.list.ListAdapter
import com.nequi.zemogatestapp.ui.list.PostListener
import kotlinx.android.synthetic.main.description_fragment.*
import kotlinx.android.synthetic.main.description_fragment.view.*
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_fragment.list_recycler

class DescriptionFragment : Fragment() {

    private lateinit var post: Post
    private lateinit var viewModel: DescriptionViewModel
    private lateinit var binding: DescriptionFragmentBinding
    private lateinit var descriptionRecyclerView: RecyclerView
    private lateinit var adapter: DescriptionAdapter

    companion object {
        fun newInstance() = DescriptionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DescriptionViewModel::class.java)

        viewModel.commentsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding = DataBindingUtil.inflate(inflater, R.layout.description_fragment, container, false)

        viewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                user ->
                    binding.bodyTextview.text = post.body
                    binding.nameTextview.text = user.name
                    binding.emailTextview.text = user.email
                    binding.phoneTextview.text = user.phone
                    binding.webTextview.text = user.website
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = DescriptionAdapter()
        descriptionRecyclerView = comments_recycler
        descriptionRecyclerView.adapter = adapter
        descriptionRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            post = GsonBuilder().create().fromJson<Post>(requireArguments().getString("post"), Post::class.java)
        }
    }
}