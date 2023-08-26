package com.example.testapp.data.cache.source.camera

import com.example.testapp.data.data.models.CameraDataModel
import kotlinx.coroutines.flow.Flow

interface CameraCacheDataSource {

    fun fetchAllCameraFromCacheObservable(): Flow<List<CameraDataModel>>

    suspend fun fetchAllCameraFromCacheSingle(): List<CameraDataModel>

    suspend fun addNewCameraToCache(camera: CameraDataModel)

    suspend fun clearTable()

}