package com.example.testapp.presentation.di

import com.example.testapp.presentation.mappers.MapFromCameraDomainToUiModel
import com.example.testapp.presentation.mappers.MapFromDoorDomainToUiModel
import com.example.testapp.presentation.ui.screen_camera.FragmentCameraViewModel
import com.example.testapp.presentation.ui.screen_door.FragmentDoorViewModel
import com.example.testapp.presentation.ui.scrren_door_info.DoorInfoDialogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {

    viewModel {
        FragmentCameraViewModel(
            cameraUseCase = get(),
            dispatchersProvider = get(),
            mapFromCameraDomainToUiModel = MapFromCameraDomainToUiModel()
        )
    }
    viewModel {
        FragmentDoorViewModel(
            doorUseCase = get(),
            dispatchersProvider = get(),
            mapFromDoorDomainToUiModel = MapFromDoorDomainToUiModel()
        )
    }

    viewModel { (id: String) ->
        DoorInfoDialogViewModel(
            id = id,
            doorUseCase = get(),
            mapFromDoorDomainToUi = MapFromDoorDomainToUiModel()
        )
    }


}