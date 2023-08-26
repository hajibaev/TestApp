package com.example.testapp.presentation.ui.screen_camera

import androidx.lifecycle.ViewModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.CameraDomainModel
import com.example.testapp.domain.use_case.CameraUseCase
import com.example.testapp.presentation.models.CameraUiModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FragmentCameraViewModel(
    cameraUseCase: CameraUseCase,
    dispatchersProvider: DispatchersProvider,
    private val mapFromCameraDomainToUiModel: Mapper<CameraDomainModel, CameraUiModel>
) : ViewModel() {

    val camera = cameraUseCase.invoke()
        .flowOn(dispatchersProvider.default())
        .map { camera -> camera.map(mapFromCameraDomainToUiModel::map) }

}