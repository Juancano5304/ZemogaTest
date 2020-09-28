package com.nequi.zemogatestapp.repository

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZemogaRepository(private val postDao: PostDao, private val userDao: UserDao, private val commentDao: CommentDao) {
    val getAll: LiveData<List<Post>> = postDao.getAll()
    val getUser: LiveData<User> = userDao.getUser()
    val getComments: LiveData<List<Comment>> = commentDao.getComments()
    val getFavorites: LiveData<List<Post>> = postDao.getFavorites()

    fun readPost(postId: Int) {
        return postDao.readPost(postId, 1)
    }

    fun updateFavorite(post: Post)
    {
        return when(post.favorite) {
            true -> postDao.updateFavorite(post.id, 0)
            false -> postDao.updateFavorite(post.id, 1)
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

    private fun insertUser(user: User) {
        userDao.insert(user)
    }

    private fun deleteUser() {
        userDao.deleteUser()
    }

    private fun deleteComments() {
        commentDao.deleteComments()
    }

    private fun insertComment(comment: Comment) {
        commentDao.insert(comment)
    }

    fun callServiceGetPosts(context: Context) {
        val postsService: JsonPlaceHolderService = RestEngine.getRestEngine()
            .create(JsonPlaceHolderService::class.java)
        val result: Call<List<Post>> = postsService.getAll()
        result.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(context, "No se puede acceder al servicio", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                when(response.code()) {
                    200 -> {
                        Toast.makeText(context, "Posts cargados", Toast.LENGTH_SHORT).show()
                        AsyncTask.execute {
                            deleteAll()
                            for(item in response.body()!!) {
                                item.favorite = false
                                item.read = item.id > 20
                            }
                            insert(response.body()!!)
                        }
                    }
                }
            }
        })
    }

    fun callServiceGetUser(context: Context, userId: Int) {
        val jsonPlaceHolderService: JsonPlaceHolderService = RestEngine.getRestEngine()
            .create(JsonPlaceHolderService::class.java)
        val result: Call<User> = jsonPlaceHolderService.getUser(userId)
        result.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "No se puede acceder al servicio", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                when(response.code()) {
                    200 -> {
                        Toast.makeText(context, "Usuario obtenido", Toast.LENGTH_SHORT).show()
                        AsyncTask.execute {
                            deleteUser()
                            val body = response.body()!!
                            val user = User(body.email, 0, body.name, body.phone, body.website)
                            insertUser(user)
                        }
                    }
                }
            }
        })
    }

    fun callServiceGetComments(context: Context, postId: Int) {
        val jsonPlaceHolderService: JsonPlaceHolderService = RestEngine.getRestEngine()
            .create(JsonPlaceHolderService::class.java)
        val result: Call<List<Comment>> = jsonPlaceHolderService.getComments(postId)
        result.enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(context, "No se puede acceder al servicio", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                when(response.code()) {
                    200 -> {
                        Toast.makeText(context, "Comentarios obtenidos", Toast.LENGTH_SHORT).show()
                        AsyncTask.execute {
                            deleteComments()
                            val body = response.body()!!
                            for(comment in body) {
                                insertComment(Comment(0, comment.body))
                            }
                        }
                    }
                }
            }
        })
    }
}