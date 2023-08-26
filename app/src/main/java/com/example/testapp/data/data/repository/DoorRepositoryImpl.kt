package com.example.testapp.data.data.repository

import com.example.testapp.data.cache.models.DoorCache
import com.example.testapp.data.cache.source.door.DoorCacheDataSource
import com.example.testapp.data.cloud.source.door.DoorDataSource
import com.example.testapp.data.cloud.takeSuccess
import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.models.DoorDomainModel
import com.example.testapp.domain.repository.DoorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DoorRepositoryImpl(
    private val cacheDataSource: DoorCacheDataSource,
    private val source: DoorDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromDoorDataToDomain: Mapper<DoorDataModel, DoorDomainModel>,
    private val mapFromDoorCacheToData: Mapper<DoorCache, DoorDataModel>,
) : DoorRepository {

    override fun fetchAllDoors(): Flow<List<DoorDomainModel>> = flow {
        emit(cacheDataSource.fetchAllDoorFromCacheSingle())
    }.flatMapLatest { handleFetchDoorsInCache(it) }
        .map { doors -> doors.map(mapFromDoorDataToDomain::map) }
        .flowOn(dispatchersProvider.default())

    override fun fetchAllDoorsObservable(id: Int): Flow<DoorDomainModel> =
        cacheDataSource.fetchDoorFromId(id = id).map { doorsFromCache ->
            if (doorsFromCache == null) source.fetchDoorsById(id = id)
                .takeSuccess() else mapFromDoorCacheToData.map(doorsFromCache)
        }.map { it ?: DoorDataModel.unknown() }.map(mapFromDoorDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    private fun handleFetchDoorsInCache(
        cachedDoors: List<DoorDataModel>,
    ) = if (cachedDoors.isEmpty()) source.fetchDoors().onEach { doors ->
        doors.forEach { cacheDataSource.addNewDoorToCache(it) }
    }
    else cacheDataSource.fetchAllDoorFromCacheObservable()

}