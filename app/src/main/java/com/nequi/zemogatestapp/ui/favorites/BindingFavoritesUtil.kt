package com.nequi.zemogatestapp.ui.favorites

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import com.nequi.zemogatestapp.repository.Comment
import com.nequi.zemogatestapp.repository.Post

@BindingAdapter("textTitle")
fun MaterialTextView.setTextTitle(post: Post?) {
    post?.let {
        text = post.title
    }
}

@BindingAdapter("textBody")
fun MaterialTextView.setTextBody(post: Post?) {
    post?.let {
        text = post.body
    }
}