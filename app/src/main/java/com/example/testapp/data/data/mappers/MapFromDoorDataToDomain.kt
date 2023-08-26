package com.example.testapp.data.data.mappers

import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.DoorDomainModel

class MapFromDoorDataToDomain : Mapper<DoorDataModel, DoorDomainModel> {
    override fun map(from: DoorDataModel) = from.run {
        DoorDomainModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
        )
    }
}