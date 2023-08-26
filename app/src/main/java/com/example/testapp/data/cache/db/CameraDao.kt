package com.example.testapp.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.cache.models.CameraCache
import kotlinx.coroutines.flow.Flow

@Dao
interface CameraDao {

    @Query("select * from cameras_table")
    fun fetchAllCameraObservable(): Flow<MutableList<CameraCache>>

    @Query("select * from cameras_table")
    suspend fun fetchAllCameraSingle(): MutableList<CameraCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewCamera(food: CameraCache)

    @Query("DELETE FROM cameras_table")
    fun clearTable()
}