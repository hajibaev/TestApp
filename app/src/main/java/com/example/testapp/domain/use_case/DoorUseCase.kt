package com.example.testapp.domain.use_case

import com.example.testapp.domain.models.DoorDomainModel
import com.example.testapp.domain.repository.DoorRepository
import kotlinx.coroutines.flow.Flow

interface DoorUseCase {

    operator fun invoke(): Flow<List<DoorDomainModel>>

    operator fun invoke(id: String): Flow<DoorDomainModel>

}


class DoorUseCaseImpl(private val repository: DoorRepository) :
    DoorUseCase {

    override fun invoke(): Flow<List<DoorDomainModel>> = repository.fetchAllDoors()


    override fun invoke(id: String): Flow<DoorDomainModel> =
        repository.fetchAllDoorsObservable(id = id.toInt())

}
