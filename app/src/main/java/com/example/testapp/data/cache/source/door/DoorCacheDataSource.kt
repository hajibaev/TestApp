package com.example.testapp.data.cache.source.door

import com.example.testapp.data.cache.models.DoorCache
import com.example.testapp.data.data.models.DoorDataModel
import kotlinx.coroutines.flow.Flow

interface DoorCacheDataSource {

    fun fetchAllDoorFromCacheObservable(): Flow<List<DoorDataModel>>

    suspend fun fetchAllDoorFromCacheSingle(): List<DoorDataModel>

    suspend fun addNewDoorToCache(dishes: DoorDataModel)

    fun fetchDoorFromId(id: Int): Flow<DoorCache?>

    suspend fun clearTable()

}