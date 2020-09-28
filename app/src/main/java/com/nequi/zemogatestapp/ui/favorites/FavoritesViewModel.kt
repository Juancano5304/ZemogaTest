package com.nequi.zemogatestapp.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.repository.ZemogaDatabase
import com.nequi.zemogatestapp.repository.ZemogaRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ZemogaRepository
    val allFavorites: LiveData<List<Post>>
    private val applicationContext: Application

    init {
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        val userDao = ZemogaDatabase.getDatabase(application).userDao()
        val commentDao = ZemogaDatabase.getDatabase(application).commentDao()
        repository = ZemogaRepository(postDao, userDao, commentDao)
        applicationContext = application
        allFavorites = repository.getFavorites
    }
}