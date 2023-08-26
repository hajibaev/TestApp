package com.example.testapp.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.cache.models.DoorCache
import kotlinx.coroutines.flow.Flow

@Dao
interface DoorDao {

    @Query("select * from door_table")
    fun fetchAllDoorObservable(): Flow<MutableList<DoorCache>>

    @Query("select * from door_table")
    suspend fun fetchAllDoorSingle(): MutableList<DoorCache>

    @Query("select * from door_table where id == :id")
    fun fetchDoorFromId(id: Int): Flow<DoorCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewDoor(door: DoorCache)

    @Query("DELETE FROM door_table")
    fun clearTable()
}