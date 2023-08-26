package com.example.testapp.data.cache.source.door

import com.example.testapp.data.cache.db.DoorDao
import com.example.testapp.data.cache.models.DoorCache
import com.example.testapp.data.data.models.DoorDataModel
import com.example.testapp.domain.DispatchersProvider
import com.example.testapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DoorCacheDataSourceImpl(
    private val dao: DoorDao,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromDoorCacheToData: Mapper<DoorCache, DoorDataModel>,
    private val mapFromDoorDataToCache: Mapper<DoorDataModel, DoorCache>,
) : DoorCacheDataSource {

    override fun fetchAllDoorFromCacheObservable(): Flow<List<DoorDataModel>> =
        dao.fetchAllDoorObservable()
            .flowOn(dispatchersProvider.io())
            .map { dishes -> dishes.map(mapFromDoorCacheToData::map) }
            .flowOn(dispatchersProvider.default())

    override suspend fun fetchAllDoorFromCacheSingle(): List<DoorDataModel> {
        val cachedList = dao.fetchAllDoorSingle()
        return cachedList.map(mapFromDoorCacheToData::map)
    }

    override suspend fun addNewDoorToCache(dishes: DoorDataModel) =
        dao.addNewDoor(mapFromDoorDataToCache.map(dishes))

    override fun fetchDoorFromId(id: Int): Flow<DoorCache?> =
        dao.fetchDoorFromId(id)
            .flowOn(dispatchersProvider.io())

    override suspend fun clearTable() = dao.clearTable()
}