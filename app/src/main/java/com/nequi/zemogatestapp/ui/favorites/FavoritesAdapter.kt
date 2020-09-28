package com.nequi.zemogatestapp.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nequi.zemogatestapp.databinding.CommentElementBinding
import com.nequi.zemogatestapp.databinding.FavoriteElementBinding
import com.nequi.zemogatestapp.repository.Comment
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.ui.description.DescriptionViewModel

class FavoritesAdapter: androidx.recyclerview.widget.ListAdapter<Post, FavoritesAdapter.MyViewHolder>(PostDiffCallback()) {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var context: Context

    class MyViewHolder private constructor(val binding: FavoriteElementBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteElementBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    fun getPosition(adapterPosition: Int): Int {
        return getItem(adapterPosition).id
    }
}

class PostDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}