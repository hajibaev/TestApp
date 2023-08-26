package com.example.testapp.data.cloud.source.camera

import com.example.testapp.data.data.models.CameraDataModel
import kotlinx.coroutines.flow.Flow

interface CameraDataSource {

    fun fetchCameras(): Flow<List<CameraDataModel>>


}