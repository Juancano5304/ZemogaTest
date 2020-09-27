package com.nequi.zemogatestapp.ui.list

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import com.nequi.zemogatestapp.repository.Post

@BindingAdapter("textTitle")
fun MaterialTextView.setTextTitle(post: Post?) {
    post?.let {
        text = post.title
    }
}

@BindingAdapter("readVisibility")
fun AppCompatImageView.setReadImage(post: Post?) {
    post?.let {
        visibility = when(post.read) {
            true -> View.INVISIBLE
            false -> View.VISIBLE
        }
    }
}

@BindingAdapter("favoriteVisibility")
fun AppCompatImageView.setfavoriteImage(post: Post?){
    post?.let {
        visibility = when(post.favorite) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
    }
}