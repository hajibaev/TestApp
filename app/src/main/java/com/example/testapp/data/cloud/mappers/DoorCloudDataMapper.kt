package com.example.testapp.data.cloud.mappers

import com.example.testapp.data.cloud.models.DoorCloudModel
import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.Mapper

class DoorCloudDataMapper : Mapper<DoorCloudModel, DoorDataModel> {
    override fun map(from: DoorCloudModel) = from.run {
        DoorDataModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
        )
    }
}