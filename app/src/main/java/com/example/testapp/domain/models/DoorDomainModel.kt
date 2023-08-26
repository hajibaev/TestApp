package com.example.testapp.domain.models

data class DoorDomainModel(
    val id: Int,
    var name: String,
    val snapshot: String?,
    val room: String?,
    val favorites: Boolean,
)