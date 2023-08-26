package com.example.testapp.data.cache.source.camera

import com.example.testapp.data.cache.db.CameraDao
import com.example.testapp.data.cache.models.CameraCache
import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CameraCacheDataSourceImpl(
    private val dao: CameraDao,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromCameraCacheToData: Mapper<CameraCache, CameraDataModel>,
    private val mapFromCameraDataToCache: Mapper<CameraDataModel, CameraCache>,
) : CameraCacheDataSource {

    override fun fetchAllCameraFromCacheObservable(): Flow<List<CameraDataModel>> =
        dao.fetchAllCameraObservable()
            .flowOn(dispatchersProvider.io())
            .map { dishes -> dishes.map(mapFromCameraCacheToData::map) }
            .flowOn(dispatchersProvider.default())

    override suspend fun fetchAllCameraFromCacheSingle(): List<CameraDataModel> {
        val cachedList = dao.fetchAllCameraSingle()
        return cachedList.map(mapFromCameraCacheToData::map)
    }

    override suspend fun addNewCameraToCache(camera: CameraDataModel) =
        dao.addNewCamera(mapFromCameraDataToCache.map(camera))

    override suspend fun clearTable() = dao.clearTable()

}