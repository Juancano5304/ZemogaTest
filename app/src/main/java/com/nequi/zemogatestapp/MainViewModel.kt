package com.nequi.zemogatestapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nequi.zemogatestapp.repository.ZemogaDatabase
import com.nequi.zemogatestapp.repository.ZemogaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ZemogaRepository

    init {
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        repository = ZemogaRepository(postDao)
    }

    fun callServiceGetPosts(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.callServiceGetPosts(context)
        }
    }
}