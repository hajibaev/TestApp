package com.example.testapp.presentation.mappers

import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.DoorDomainModel
import com.example.testapp.presentation.models.DoorUiModel

class MapFromDoorDomainToUiModel : Mapper<DoorDomainModel, DoorUiModel> {
    override fun map(from: DoorDomainModel) = from.run {
        DoorUiModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
        )
    }
}