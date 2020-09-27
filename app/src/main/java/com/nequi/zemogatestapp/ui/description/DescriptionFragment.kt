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
import com.google.gson.GsonBuilder
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.databinding.DescriptionFragmentBinding
import com.nequi.zemogatestapp.databinding.DescriptionFragmentBindingImpl
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.repository.User
import kotlinx.android.synthetic.main.description_fragment.view.*

class DescriptionFragment : Fragment() {

    private lateinit var post: Post
    private lateinit var viewModel: DescriptionViewModel
    private lateinit var binding: DescriptionFragmentBinding
    private lateinit var user: User

    companion object {
        fun newInstance() = DescriptionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DescriptionViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.description_fragment, container, false)

        viewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                user ->
                    binding.bodyTextview.text = post.body
                    binding.nameTextview.text = user.name
                    binding.emailTextview.text = user.email
                    binding.phoneTextview.text = user.phone
                    binding.webTextview.text = user.website
                    //adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        /*when(post.favorite) {
            true -> binding.imageviewDetailsFavorite.setImageResource(R.drawable.ic_baseline_stars_24_yellow)
            false -> binding.imageviewDetailsFavorite.setImageResource(R.drawable.ic_baseline_stars_24)
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            bundle ->
            post = GsonBuilder().create().fromJson<Post>(requireArguments().getString("post"), Post::class.java)
        }
    }
}