package com.nequi.zemogatestapp.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.nequi.zemogatestapp.R
import com.nequi.zemogatestapp.repository.Post
import kotlinx.android.synthetic.main.list_element.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var postList = emptyList<Post>()
    private lateinit var listViewModel: ListViewModel
    private lateinit var context: Context

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        listViewModel = ViewModelProvider(context as FragmentActivity).get(ListViewModel::class.java)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = postList[position]
        holder.itemView.title_texview.text = item.title.toString()
        when(item.read) {
            true -> holder.itemView.image_circle.visibility = View.INVISIBLE
            false -> holder.itemView.image_circle.visibility = View.VISIBLE
        }

        when(item.favorite) {
            true -> holder.itemView.image_star.visibility = View.VISIBLE
            false -> holder.itemView.image_star.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
                v ->
            val bundle = Bundle()
            postList[position].read = false
            updateRead(postList[position])
            setData(postList)
            bundle.putString("post", GsonBuilder().create().toJson(item))
            Navigation.findNavController(v).navigate(R.id.action_listFragment_to_descriptionFragment, bundle)
        }
    }

    private fun updateRead(post: Post) {
        listViewModel.updateRead(post)
    }

    fun setData(post: List<Post>) {
        this.postList = post
        Log.i("prueba", post.toString())
        notifyDataSetChanged()
    }

    fun getPosition(adapterPosition: Int): Int {
        return postList[adapterPosition].id
    }
}