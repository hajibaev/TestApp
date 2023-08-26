package com.example.testapp.presentation.di

import com.example.testapp.data.cache.mappers.MapFromCameraCacheToData
import com.example.testapp.data.cache.mappers.MapFromCameraDataToCache
import com.example.testapp.data.cache.mappers.MapFromDoorCacheToData
import com.example.testapp.data.cache.mappers.MapFromDoorDataToCache
import com.example.testapp.data.cache.source.camera.CameraCacheDataSource
import com.example.testapp.data.cache.source.camera.CameraCacheDataSourceImpl
import com.example.testapp.data.cache.source.door.DoorCacheDataSource
import com.example.testapp.data.cache.source.door.DoorCacheDataSourceImpl
import com.example.testapp.data.cloud.mappers.DoorCloudDataMapper
import com.example.testapp.data.cloud.mappers.MapFromCameraCloudToData
import com.example.testapp.data.cloud.mappers.MapFromDoorResponseToCloud
import com.example.testapp.data.cloud.source.camera.CameraDataSource
import com.example.testapp.data.cloud.source.camera.CameraDataSourceImpl
import com.example.testapp.data.cloud.source.door.DoorDataSource
import com.example.testapp.data.cloud.source.door.DoorDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    /**
     * CloudDataSource
     */

    single<CameraDataSource> {
        CameraDataSourceImpl(
            service = get(),
            mapFromCameraCloudToData = MapFromCameraCloudToData(),
            dispatchersProvider = get()
        )
    }

    single<DoorDataSource> {
        DoorDataSourceImpl(
            service = get(),
            dispatchersProvider = get(),
            responseHandler = get(),
            mapFromDoorCloudToData = DoorCloudDataMapper(),
            mapFromDoorResponseToCloud = MapFromDoorResponseToCloud(),
        )
    }


    /**
     * CacheDataSource
     */

    single<CameraCacheDataSource> {
        CameraCacheDataSourceImpl(
            dao = get(),
            dispatchersProvider = get(),
            mapFromCameraCacheToData = MapFromCameraCacheToData(),
            mapFromCameraDataToCache = MapFromCameraDataToCache(),
        )
    }

    single<DoorCacheDataSource> {
        DoorCacheDataSourceImpl(
            dao = get(),
            dispatchersProvider = get(),
            mapFromDoorCacheToData = MapFromDoorCacheToData(),
            mapFromDoorDataToCache = MapFromDoorDataToCache()
        )
    }


}