package com.nequi.zemogatestapp.ui.description

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nequi.zemogatestapp.repository.*
import com.nequi.zemogatestapp.repository.ZemogaDatabase.Companion.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DescriptionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ZemogaRepository
    val user: LiveData<User>
    val postList: LiveData<List<Post>>
    val commentsList: LiveData<List<Comment>>

    init {
        val userDao = ZemogaDatabase.getDatabase(application).userDao()
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        val commentDao = ZemogaDatabase.getDatabase(application).commentDao()
        repository = ZemogaRepository(postDao, userDao, commentDao)
        user = repository.getUser
        postList = repository.getAll
        commentsList = repository.getComments
    }

    fun setFavorite(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavorite(post)
        }
    }
}