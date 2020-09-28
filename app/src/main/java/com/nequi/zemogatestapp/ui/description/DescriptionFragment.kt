package com.nequi.zemogatestapp.ui.description

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.databinding.DescriptionFragmentBinding
import com.nequi.zemogatestapp.repository.Post
import kotlinx.android.synthetic.main.description_fragment.*

class DescriptionFragment : Fragment() {

    private lateinit var post: Post
    private lateinit var viewModel: DescriptionViewModel
    private lateinit var binding: DescriptionFragmentBinding
    private lateinit var descriptionRecyclerView: RecyclerView
    private lateinit var adapter: DescriptionAdapter
    private lateinit var menu: Menu
    private var temporalFavorite: Boolean = false

    companion object {
        fun newInstance() = DescriptionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //requireActivity().findViewById<Toolbar>(R.id.mainactivity_toolbar).menu.close()

        viewModel = ViewModelProvider(this).get(DescriptionViewModel::class.java)

        viewModel.commentsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding = DataBindingUtil.inflate(inflater, R.layout.description_fragment, container, false)

        viewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let { user ->
                binding.bodyTextview.text = post.body
                binding.nameTextview.text = user.name
                binding.emailTextview.text = user.email
                binding.phoneTextview.text = user.phone
                binding.webTextview.text = user.website
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        this.menu = menu
        when(temporalFavorite) {
            false ->
                menu.getItem(0).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_star_border_24)
            true ->
                menu.getItem(0).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_star_24)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.favorite) {
            when(temporalFavorite) {
                true -> {
                    menu.getItem(0).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_star_border_24)
                    temporalFavorite = false
                    Toast.makeText(requireContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
                    viewModel.setFavorite(Post(post.userId, post.id-1, post.title, post.body, !temporalFavorite, post.read))
                }
                false -> {
                    menu.getItem(0).icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_star_24)
                    temporalFavorite = true
                    Toast.makeText(requireContext(), "Ahora este post es favorito", Toast.LENGTH_SHORT).show()
                    viewModel.setFavorite(Post(post.userId, post.id-1, post.title, post.body, !temporalFavorite, post.read))
                }
            }
            return true
        }
        return false
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
            post = GsonBuilder().create().fromJson<Post>(
                requireArguments().getString("post"),
                Post::class.java
            )
        }
        temporalFavorite = post.favorite
    }
}