package com.nequi.zemogatestapp.repository

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(
    @ColumnInfo(name = "user_id") @NonNull var userId: Int,
    @PrimaryKey @ColumnInfo(name = "id") @NonNull var id: Int,
    @ColumnInfo(name = "title") @NonNull var title: String,
    @ColumnInfo(name = "body") @NonNull var body: String,
    @ColumnInfo(name = "favorite") @NonNull var favorite: Boolean,
    @ColumnInfo(name = "read") @NonNull var read: Boolean)