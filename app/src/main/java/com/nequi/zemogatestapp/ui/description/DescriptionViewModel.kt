package com.nequi.zemogatestapp.ui.description

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.repository.User
import com.nequi.zemogatestapp.repository.ZemogaDatabase
import com.nequi.zemogatestapp.repository.ZemogaDatabase.Companion.getDatabase
import com.nequi.zemogatestapp.repository.ZemogaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DescriptionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ZemogaRepository
    val user: LiveData<User>
    val postList: LiveData<List<Post>>

    init {
        val userDao = ZemogaDatabase.getDatabase(application).userDao()
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        repository = ZemogaRepository(postDao, userDao)
        user = repository.getUser
        postList = repository.getAll
    }
}