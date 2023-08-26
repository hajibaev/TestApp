package com.example.testapp.data.data.mappers

import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.CameraDomainModel

class MapFromCameraDataToDomain : Mapper<CameraDataModel, CameraDomainModel> {
    override fun map(from: CameraDataModel) = from.run {
        CameraDomainModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
            rec = rec
        )
    }
}