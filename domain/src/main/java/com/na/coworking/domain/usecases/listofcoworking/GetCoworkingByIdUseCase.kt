package com.na.coworking.domain.usecases.listofcoworking

import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository

class GetCoworkingByIdUseCase(
    private val repository: ListOfCoworkingRepository
) {
    suspend operator fun invoke(id: Int): Workspace {
        return repository.getById(id)
    }
}