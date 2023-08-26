package com.example.testapp.data.cache.mappers

import com.example.testapp.data.cache.models.CameraCache
import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.Mapper

class MapFromCameraDataToCache : Mapper<CameraDataModel, CameraCache> {
    override fun map(from: CameraDataModel) = from.run {
        CameraCache(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
            rec = rec
        )
    }
}