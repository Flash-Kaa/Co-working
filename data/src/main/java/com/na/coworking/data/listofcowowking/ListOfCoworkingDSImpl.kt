package com.na.coworking.data.listofcowowking

import com.na.coworking.data.network.ApiService
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ListOfCoworkingDSImpl(
    private val service: ApiService
) : ListOfCoworkingDataSource {

    private val _items: MutableStateFlow<List<Workspace>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<Workspace>> = _items.asStateFlow()

    override suspend fun getList(): Flow<List<Workspace>> {
        val objTemplates = service.getTemplatesObjects()
        val amenityTemplates = service.getTemplatesAmenities()

        val workspaces = service.getWorkspaces().map { workspace ->
            workspace.toWorkspace(
                objTemplates = objTemplates.map { it.toObjTemplate() },
                amenityTemplate = amenityTemplates.map { it.toAmenityTemplate() }
            )
        }

        _items.update { workspaces }
        return items
    }

    override suspend fun getById(id: Int): Workspace {
        return items.value.first { it.id == id }
    }
}