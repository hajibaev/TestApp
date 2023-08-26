package com.example.testapp.domain.repository

import com.example.testapp.domain.models.CameraDomainModel
import kotlinx.coroutines.flow.Flow

interface CameraRepository {

    fun fetchAllCamera(): Flow<List<CameraDomainModel>>

}