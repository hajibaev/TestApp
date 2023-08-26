package com.example.testapp.domain.use_case

import com.example.testapp.domain.models.CameraDomainModel
import com.example.testapp.domain.repository.CameraRepository
import kotlinx.coroutines.flow.Flow

interface CameraUseCase {

    fun invoke(): Flow<List<CameraDomainModel>>

}

class CameraUseCaseImpl(private val repository: CameraRepository) : CameraUseCase {
    override fun invoke(): Flow<List<CameraDomainModel>> = repository.fetchAllCamera()
}