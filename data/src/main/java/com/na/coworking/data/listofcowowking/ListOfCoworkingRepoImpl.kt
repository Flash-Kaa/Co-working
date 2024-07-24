package com.na.coworking.data.listofcowowking

import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingDataSource
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository
import kotlinx.coroutines.flow.Flow

class ListOfCoworkingRepoImpl(
    private val dataSource: ListOfCoworkingDataSource
) : ListOfCoworkingRepository {
    override suspend fun getList(): Flow<List<Workspace>> {
        return dataSource.getList()
    }

    override suspend fun getById(id: Int): Workspace {
        return dataSource.getById(id)
    }
}