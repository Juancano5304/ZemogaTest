package com.nequi.zemogatestapp.repository

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true) @NonNull var id: Int = 0,
    @ColumnInfo(name = "body") @NonNull var body: String
)