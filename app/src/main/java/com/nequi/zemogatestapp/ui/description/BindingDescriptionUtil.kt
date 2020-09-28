package com.nequi.zemogatestapp.ui.description

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import com.nequi.zemogatestapp.repository.Comment
import com.nequi.zemogatestapp.repository.Post

@BindingAdapter("textTitle")
fun MaterialTextView.setTextTitle(comment: Comment?) {
    comment?.let {
        text = comment.body
    }
}