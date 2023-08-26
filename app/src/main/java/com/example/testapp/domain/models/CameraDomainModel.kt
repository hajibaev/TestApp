package com.example.testapp.domain.models

data class CameraDomainModel(
    val id: Int,
    val name: String,
    val snapshot: String,
    val room: String?,
    val favorites: Boolean,
    val rec: Boolean
)