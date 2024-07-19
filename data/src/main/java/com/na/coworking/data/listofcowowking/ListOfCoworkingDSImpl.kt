package com.na.coworking.data.listofcowowking

import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ListOfCoworkingDSImpl(
// TODO: private val service: ServiceAPIWhereGetList
) : ListOfCoworkingDataSource {
    private val _items: MutableStateFlow<List<Workspace>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<Workspace>> = _items.asStateFlow()

    override suspend fun getList(): Flow<List<Workspace>> {
        // TODO: _items.update { service.get() }
        return items
    }
}