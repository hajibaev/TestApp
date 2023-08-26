package com.example.testapp.presentation.mappers

import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.CameraDomainModel
import com.example.testapp.presentation.models.CameraUiModel

class MapFromCameraDomainToUiModel : Mapper<CameraDomainModel, CameraUiModel> {
    override fun map(from: CameraDomainModel) = from.run {
        CameraUiModel(
            id = id,
            name = name,
            snapshot = snapshot,
            room = room,
            favorites = favorites,
            rec = rec
        )
    }
}