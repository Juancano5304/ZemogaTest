package com.nequi.zemogatestapp.repository

import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    fun getAll(): Call<List<Post>>
}