package com.example.testapp.presentation.di

import com.example.testapp.data.cache.mappers.MapFromDoorCacheToData
import com.example.testapp.data.data.mappers.MapFromCameraDataToDomain
import com.example.testapp.data.data.mappers.MapFromDoorDataToDomain
import com.example.testapp.data.data.repository.CameraRepositoryImpl
import com.example.testapp.data.data.repository.DoorRepositoryImpl
import com.example.testapp.domain.repository.CameraRepository
import com.example.testapp.domain.repository.DoorRepository
import org.koin.dsl.module

val repositoryModule = module {


    single<CameraRepository> {
        CameraRepositoryImpl(
            source = get(),
            cacheDataSource = get(),
            dispatchersProvider = get(),
            mapFromCameraDataToDomain = MapFromCameraDataToDomain(),
        )
    }

    single<DoorRepository> {
        DoorRepositoryImpl(
            source = get(),
            cacheDataSource = get(),
            dispatchersProvider = get(),
            mapFromDoorDataToDomain = MapFromDoorDataToDomain(),
            mapFromDoorCacheToData = MapFromDoorCacheToData()
        )
    }

}