package com.example.testapp.data.cloud.models

data class DoorResponseCloudModel(
    val success: Boolean = true,
    val data: List<DoorCloudModel>
)

data class DoorCloudModel(
    val id: Int,
    var name: String,
    val snapshot: String?,
    val room: String?,
    val favorites: Boolean,
)

