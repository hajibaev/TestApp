package com.example.testapp.data.cloud.source.camera

import com.example.testapp.data.cloud.models.CameraCloud
import com.example.testapp.data.cloud.service.CameraService
import com.example.testapp.data.data.models.CameraDataModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CameraDataSourceImpl(
    private val service: CameraService,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromCameraCloudToData: Mapper<CameraCloud, CameraDataModel>
) : CameraDataSource {


    override fun fetchCameras(): Flow<List<CameraDataModel>> = flow {
        emit(service.fetchAllCameras())
    }.flowOn(dispatchersProvider.io())
        .map { it.body()!! }
        .map { it.data.camera }
        .map { camera -> camera.map(mapFromCameraCloudToData::map) }
        .flowOn(dispatchersProvider.default())

}