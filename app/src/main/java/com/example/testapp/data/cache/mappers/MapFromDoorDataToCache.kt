package com.example.testapp.data.cache.mappers

import com.example.testapp.data.cache.models.DoorCache
import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.Mapper

class MapFromDoorDataToCache : Mapper<DoorDataModel, DoorCache> {
    override fun map(from: DoorDataModel) = from.run {
        DoorCache(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
        )
    }
}