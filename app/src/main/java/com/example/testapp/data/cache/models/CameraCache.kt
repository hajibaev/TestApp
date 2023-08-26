package com.example.testapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cameras_table")
data class CameraCache(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "snapshot") val snapshot: String,
    @ColumnInfo(name = "room") val room: String?,
    @ColumnInfo(name = "favorites") val favorites: Boolean,
    @ColumnInfo(name = "rec") val rec: Boolean
)