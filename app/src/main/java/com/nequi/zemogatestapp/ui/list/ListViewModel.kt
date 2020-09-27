package com.nequi.zemogatestapp.ui.list

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.nequi.zemogatestapp.repository.Post
import com.nequi.zemogatestapp.repository.ZemogaDatabase
import com.nequi.zemogatestapp.repository.ZemogaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ZemogaRepository
    val allPosts: LiveData<List<Post>>
    private val applicationContext: Application
    var progressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _navigateToDescription= MutableLiveData<Int>()
    val navigateToDescription
        get() = _navigateToDescription

    init {
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        val userDao = ZemogaDatabase.getDatabase(application).userDao()
        repository = ZemogaRepository(postDao, userDao)
        applicationContext = application
        allPosts = repository.getAll
    }

    fun postClicked(id: Int) {
        _navigateToDescription.value = id
    }

    fun descriptionNavigated() {
        _navigateToDescription.value = null
    }

    fun updateRead(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.readPost(post)
            Log.i("prueba", "registro readen actualizado")
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            Log.i("prueba", "registros borrados")
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

    fun callServiceGetUser(context: Context, userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.callServiceGetUser(context, userId)
        }
    }
}