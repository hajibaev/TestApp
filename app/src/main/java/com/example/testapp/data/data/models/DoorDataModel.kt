package com.example.testapp.data.data.models

data class DoorDataModel(
    val id: Int,
    var name: String,
    val snapshot: String?,
    val room: String?,
    val favorites: Boolean,
) {

    companion object {
        fun unknown() = DoorDataModel(
            id = String().toInt(),
            name = String(),
            snapshot = String(),
            room = String(),
            favorites = false,
        )
    }
}