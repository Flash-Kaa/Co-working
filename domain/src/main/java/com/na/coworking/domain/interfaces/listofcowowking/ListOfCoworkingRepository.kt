package com.na.coworking.domain.interfaces.listofcowowking

import com.na.coworking.domain.entities.Workspace
import kotlinx.coroutines.flow.Flow

interface ListOfCoworkingRepository {
    suspend fun getList(): Flow<List<Workspace>>

    suspend fun getById(id: Int): Workspace
}