package com.example.testapp.presentation.models

data class CameraUiModel(
    val id: Int,
    val name: String,
    val snapshot: String,
    val room: String?,
    val favorites: Boolean,
    val rec: Boolean
)