package com.nequi.zemogatestapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM post ORDER BY id ASC")
    fun getAll(): LiveData<List<Post>>

    @Query("SELECT * FROM post WHERE id = :postId")
    fun getDescription(postId: Int): LiveData<Post>

    @Query("DELETE FROM post")
    fun deleteAll()

    @Query("DELETE FROM post WHERE id = :id")
    fun delete(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postList: List<Post>)

    @Query("UPDATE post SET read = :read WHERE id = :id")
    fun readPost(id: Int, read: Int)

    @Query("UPDATE post SET favorite = :favorite WHERE id = :id")
    fun updateFavorite(id: Int, favorite: Int)
}