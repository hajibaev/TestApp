package com.example.testapp.data.cloud.source.door

import com.example.testapp.data.cloud.CloudDataRequestState
import com.example.testapp.data.data.models.DoorDataModel
import kotlinx.coroutines.flow.Flow

interface DoorDataSource {

    fun fetchDoors(): Flow<List<DoorDataModel>>

    suspend fun fetchDoorsById(id: Int): CloudDataRequestState<DoorDataModel>

}