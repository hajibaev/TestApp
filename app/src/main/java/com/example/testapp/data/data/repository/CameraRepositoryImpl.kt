package com.example.testapp.data.data.repository

import com.example.testapp.data.cache.source.camera.CameraCacheDataSource
import com.example.testapp.data.cloud.source.camera.CameraDataSource
import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.CameraDomainModel
import com.example.testapp.domain.repository.CameraRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CameraRepositoryImpl(
    private val cacheDataSource: CameraCacheDataSource,
    private val source: CameraDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromCameraDataToDomain: Mapper<CameraDataModel, CameraDomainModel>,
) : CameraRepository {

    override fun fetchAllCamera(): Flow<List<CameraDomainModel>> = flow {
        emit(cacheDataSource.fetchAllCameraFromCacheSingle())
    }.flatMapLatest { handleFetchCamerasInCache(it) }
        .map { camera -> camera.map(mapFromCameraDataToDomain::map) }
        .flowOn(dispatchersProvider.default())

    private fun handleFetchCamerasInCache(
        cachedDishes: List<CameraDataModel>,
    ) = if (cachedDishes.isEmpty())
        source.fetchCameras().onEach { camera ->
            camera.forEach { cacheDataSource.addNewCameraToCache(it) }
        }
    else cacheDataSource.fetchAllCameraFromCacheObservable()


}