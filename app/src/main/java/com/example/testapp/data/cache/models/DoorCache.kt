package com.example.testapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "door_table")
data class DoorCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("name")
    var name: String,
    @ColumnInfo("snapshot")
    val snapshot: String?,
    @ColumnInfo("room")
    val room: String?,
    @ColumnInfo("favorites")
    val favorites: Boolean,
)

