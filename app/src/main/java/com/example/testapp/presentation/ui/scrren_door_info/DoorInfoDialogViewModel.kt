package com.example.testapp.presentation.ui.scrren_door_info

import androidx.lifecycle.ViewModel
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.DoorDomainModel
import com.example.testapp.domain.use_case.DoorUseCase
import com.example.testapp.presentation.models.DoorUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class DoorInfoDialogViewModel(
    id: String,
    private val doorUseCase: DoorUseCase,
    private val mapFromDoorDomainToUi: Mapper<DoorDomainModel, DoorUiModel>
) : ViewModel() {

    private val doorsIdFlow = MutableStateFlow(id)

    val doors =
        doorsIdFlow.flatMapLatest(doorUseCase::invoke)
            .map(mapFromDoorDomainToUi::map)

}