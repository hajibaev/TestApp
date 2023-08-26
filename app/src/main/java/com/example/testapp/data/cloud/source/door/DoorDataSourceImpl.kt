package com.example.testapp.data.cloud.source.door

import com.example.testapp.data.base.ResponseHandler
import com.example.testapp.data.cloud.CloudDataRequestState
import com.example.testapp.data.cloud.models.DoorCloudModel
import com.example.testapp.data.cloud.models.DoorResponseCloudModel
import com.example.testapp.data.cloud.service.CameraService
import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DoorDataSourceImpl(
    private val service: CameraService,
    private val responseHandler: ResponseHandler,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromDoorCloudToData: Mapper<DoorCloudModel, DoorDataModel>,
    private val mapFromDoorResponseToCloud: Mapper<DoorResponseCloudModel, DoorCloudModel>
) : DoorDataSource {


    override fun fetchDoors(): Flow<List<DoorDataModel>> = flow {
        emit(service.getDoors())
    }.flowOn(dispatchersProvider.io())
        .map { it.body() ?: DoorResponseCloudModel(data = emptyList()) }
        .map { it.data }
        .map { door -> door.map(mapFromDoorCloudToData::map) }
        .flowOn(dispatchersProvider.default())


    override suspend fun fetchDoorsById(id: Int): CloudDataRequestState<DoorDataModel> =
        responseHandler.safeApiCall { service.getDoors() }
            .map(mapFromDoorResponseToCloud)
            .map(mapFromDoorCloudToData)

}