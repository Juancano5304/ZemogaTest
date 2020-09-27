package com.nequi.zemogatestapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class, User::class], version = 2, exportSchema = false)
abstract class ZemogaDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ZemogaDatabase? = null

        fun getDatabase(context: Context): ZemogaDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZemogaDatabase::class.java,
                    "zemoga_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}