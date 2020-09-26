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
    private val applicationContext: Application

    init {
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        repository = ZemogaRepository(postDao)
        applicationContext = application
    }

    fun callServiceGetPosts(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.callServiceGetPosts(context)
        }
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.callServiceGetPosts(applicationContext)
        }
    }
}