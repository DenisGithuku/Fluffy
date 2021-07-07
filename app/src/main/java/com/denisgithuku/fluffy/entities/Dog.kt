package com.denisgithuku.fluffy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "dog")
data class Dog(
    @ColumnInfo(name = "message")
    val message: String
)


