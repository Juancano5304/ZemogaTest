package com.nequi.zemogatestapp.ui.list

import android.app.Application
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

    init {
        val postDao = ZemogaDatabase.getDatabase(application).postDao()
        repository = ZemogaRepository(postDao)
        applicationContext = application
        allPosts = repository.getAll
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
}