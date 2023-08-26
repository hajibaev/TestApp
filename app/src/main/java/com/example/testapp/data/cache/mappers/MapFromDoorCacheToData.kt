package com.example.testapp.data.cache.mappers

import com.example.testapp.data.cache.models.DoorCache
import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.Mapper

class MapFromDoorCacheToData : Mapper<DoorCache, DoorDataModel> {
    override fun map(from: DoorCache) = from.run {
        DoorDataModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
        )
    }
}