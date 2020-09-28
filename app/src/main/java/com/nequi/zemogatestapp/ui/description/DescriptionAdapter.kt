package com.nequi.zemogatestapp.ui.description

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nequi.zemogatestapp.databinding.CommentElementBinding
import com.nequi.zemogatestapp.databinding.ListElementBinding
import com.nequi.zemogatestapp.repository.Comment
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.ui.list.ListAdapter
import com.nequi.zemogatestapp.ui.list.ListViewModel

class DescriptionAdapter: androidx.recyclerview.widget.ListAdapter<Comment, DescriptionAdapter.MyViewHolder>(PostDiffCallback()) {

    private lateinit var descriptionViewModel: DescriptionViewModel
    private lateinit var context: Context

    class MyViewHolder private constructor(val binding: CommentElementBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.comment = comment
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentElementBinding.inflate(layoutInflater, parent, false)
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

class PostDiffCallback: DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}