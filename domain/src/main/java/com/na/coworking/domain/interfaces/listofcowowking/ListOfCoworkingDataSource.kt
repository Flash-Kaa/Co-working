package com.na.coworking.domain.interfaces.listofcowowking

import com.na.coworking.domain.entities.Workspace
import kotlinx.coroutines.flow.Flow

interface ListOfCoworkingDataSource {
    suspend fun getList(): Flow<List<Workspace>>
}