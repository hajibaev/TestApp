package com.example.testapp.data.cloud.service

import com.example.testapp.data.cloud.models.CameraResponseCloudModel
import com.example.testapp.data.cloud.models.DoorResponseCloudModel
import retrofit2.Response
import retrofit2.http.GET

interface CameraService {

    @GET("rubetek/cameras/")
    suspend fun fetchAllCameras(): Response<CameraResponseCloudModel>

    @GET("rubetek/doors/")
    suspend fun getDoors(): Response<DoorResponseCloudModel>

}
