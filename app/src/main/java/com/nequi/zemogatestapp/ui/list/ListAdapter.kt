package com.nequi.zemogatestapp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nequi.zemogatestapp.databinding.ListElementBinding
import com.nequi.zemogatestapp.repository.Post

class ListAdapter(val clickListener: PostListener): ListAdapter<Post, com.nequi.zemogatestapp.ui.list.ListAdapter.MyViewHolder>(PostDiffCallback()) {

    private lateinit var listViewModel: ListViewModel
    private lateinit var context: Context

    class MyViewHolder private constructor(val binding: ListElementBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post, clickListener: PostListener) {
            binding.post = post
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListElementBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)

        /*holder.itemView.setOnClickListener {
                v ->
            val bundle = Bundle()
            item.read = false
            updateRead(item)
            //setData(postList)
            bundle.putString("post", GsonBuilder().create().toJson(item))
            Navigation.findNavController(v).navigate(R.id.action_tabsFragment_to_descriptionFragment, bundle)
        }*/
    }

    private fun updateRead(post: Post) {
        listViewModel.updateRead(post)
    }

    /*private fun setData(post: List<Post>) {
        this.postList = post
        Log.i("prueba", post.toString())
        notifyDataSetChanged()
    }*/

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

class PostListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(post: Post) = clickListener(post.id)
}