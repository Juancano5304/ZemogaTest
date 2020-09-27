package com.nequi.zemogatestapp.repository

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "email") @NonNull val email: String,
    @PrimaryKey @NonNull val id: Int,
    @ColumnInfo(name = "name") @NonNull val name: String,
    @ColumnInfo(name = "phone") @NonNull val phone: String,
    @ColumnInfo(name = "website") @NonNull val website: String
    )