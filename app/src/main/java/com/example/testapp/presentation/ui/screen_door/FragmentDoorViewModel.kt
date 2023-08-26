package com.example.testapp.presentation.ui.screen_door

import androidx.lifecycle.ViewModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.DoorDomainModel
import com.example.testapp.domain.repository.DoorRepository
import com.example.testapp.domain.use_case.DoorUseCase
import com.example.testapp.presentation.models.DoorUiModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class FragmentDoorViewModel(
    doorUseCase: DoorUseCase,
    dispatchersProvider: DispatchersProvider,
    private val mapFromDoorDomainToUiModel: Mapper<DoorDomainModel, DoorUiModel>
) : ViewModel() {

    private val _showDialog = createMutableSharedFlowAsSingleLiveEvent<Int>()
    val showDialog get() = _showDialog.asSharedFlow()

    val doors = doorUseCase.invoke()
        .flowOn(dispatchersProvider.default())
        .map { door -> door.map(mapFromDoorDomainToUiModel::map) }


    fun doorItemOnClick(id: Int) {
        _showDialog.tryEmit(id)
    }

    fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

}