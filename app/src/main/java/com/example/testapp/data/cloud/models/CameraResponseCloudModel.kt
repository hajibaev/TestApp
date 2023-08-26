package com.example.testapp.data.cloud.models

import com.google.gson.annotations.SerializedName

data class CameraResponseCloudModel(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("data") val data: CameraCloudModel = CameraCloudModel()
)

data class CameraCloudModel(
    @SerializedName("room") val room: List<String> = emptyList(),
    @SerializedName("cameras") val camera: List<CameraCloud> = emptyList()
)

data class CameraCloud(
    val id: Int = 0,
    val name: String = "",
    val snapshot: String = "",
    val room: String? = "",
    val favorites: Boolean = false,
    val rec: Boolean = false,
)
