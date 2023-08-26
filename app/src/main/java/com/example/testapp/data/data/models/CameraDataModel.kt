package com.example.testapp.data.data.models

data class CameraDataModel(
    val id: Int,
    val name: String,
    val snapshot: String,
    val room: String?,
    val favorites: Boolean,
    val rec: Boolean
)
