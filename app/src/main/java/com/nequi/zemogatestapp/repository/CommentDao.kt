package com.nequi.zemogatestapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment")
    fun getComments(): LiveData<List<Comment>>

    @Query("DELETE FROM comment")
    fun deleteComments()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comments: Comment)
}