package com.example.testapp.data.cloud.mappers

import com.example.testapp.data.cloud.models.CameraCloud
import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.Mapper

class MapFromCameraCloudToData : Mapper<CameraCloud, CameraDataModel> {
    override fun map(from: CameraCloud) = from.run {
        CameraDataModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
            rec = rec
        )
    }
}