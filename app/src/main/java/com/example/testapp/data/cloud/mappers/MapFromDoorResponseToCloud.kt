package com.example.testapp.data.cloud.mappers

import com.example.testapp.data.cloud.models.DoorCloudModel
import com.example.testapp.data.cloud.models.DoorResponseCloudModel
import com.example.testapp.domain.Mapper

class MapFromDoorResponseToCloud : Mapper<DoorResponseCloudModel, DoorCloudModel> {
    override fun map(from: DoorResponseCloudModel): DoorCloudModel {
        val door = from.data.first()
        return DoorCloudModel(
            id = door.id,
            name = door.name,
            snapshot = door.snapshot,
            room = door.room,
            favorites = door.favorites,
        )
    }
}