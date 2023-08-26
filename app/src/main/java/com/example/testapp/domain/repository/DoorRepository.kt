package com.example.testapp.domain.repository

import com.example.testapp.domain.models.DoorDomainModel
import kotlinx.coroutines.flow.Flow

interface DoorRepository {

    fun fetchAllDoors(): Flow<List<DoorDomainModel>>

    fun fetchAllDoorsObservable(id: Int): Flow<DoorDomainModel>

}