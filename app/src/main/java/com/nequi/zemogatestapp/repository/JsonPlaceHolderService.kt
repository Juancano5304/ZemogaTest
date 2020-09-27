package com.nequi.zemogatestapp.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderService {
    @GET("posts")
    fun getAll(): Call<List<Post>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<User>
}