package com.nequi.zemogatestapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Query("DELETE FROM user")
    fun deleteUser()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}