package com.nequi.zemogatestapp.repository

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZemogaRepository(private val postDao: PostDao) {
    val getAll: LiveData<List<Post>> = postDao.getAll()

    fun readPost(post: Post) {
        return postDao.readPost(post.id, 1)
    }

    fun updateFavorite(post: Post)
    {
        return when(post.favorite) {
            true -> postDao.updateFavorite(post.id, 1)
            false -> postDao.updateFavorite(post.id, 0)
        }
    }

    fun insert(postList: List<Post>) {
        postDao.insert(postList)
    }

    fun deleteAll() {
        postDao.deleteAll()
    }

    fun delete(id: Int) {
        postDao.delete(id)
    }

    fun callServiceGetPosts(context: Context) {
        val postsService: PostService = RestEngine.getRestEngine()
            .create(PostService::class.java)
        val result: Call<List<Post>> = postsService.getAll()
        result.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(context, "No se puede acceder al servicio", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                when(response.code()) {
                    200 -> {
                        Toast.makeText(context, "Datos cargados", Toast.LENGTH_SHORT).show()
                        AsyncTask.execute {
                            deleteAll()
                            for(item in response.body()!!) {
                                item.favorite = false
                                item.read = false
                                if(item.id <= 20) {
                                    item.read = true
                                }
                            }
                            insert(response.body()!!)
                        }
                    }
                }
            }
        })
    }
}