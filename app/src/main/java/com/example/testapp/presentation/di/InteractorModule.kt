package com.example.testapp.presentation.di

import com.example.testapp.domain.use_case.CameraUseCase
import com.example.testapp.domain.use_case.CameraUseCaseImpl
import com.example.testapp.domain.use_case.DoorUseCase
import com.example.testapp.domain.use_case.DoorUseCaseImpl
import org.koin.dsl.module

val interactorModule = module {

    factory<CameraUseCase> {
        CameraUseCaseImpl(repository = get())
    }

    factory<DoorUseCase> {
        DoorUseCaseImpl(repository = get())
    }

}