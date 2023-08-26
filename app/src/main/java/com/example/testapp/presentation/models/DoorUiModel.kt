package com.example.testapp.presentation.models

data class DoorUiModel(
    val id: Int,
    var name: String,
    val snapshot: String?,
    val room: String?,
    val favorites: Boolean,
)