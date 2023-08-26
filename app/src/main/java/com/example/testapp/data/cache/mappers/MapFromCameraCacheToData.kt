package com.example.testapp.data.cache.mappers

import com.example.testapp.data.cache.models.CameraCache
import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.Mapper

class MapFromCameraCacheToData : Mapper<CameraCache, CameraDataModel> {
    override fun map(from: CameraCache) = from.run {
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