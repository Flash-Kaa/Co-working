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
    private val hardData = listOf(
        Workspace(
            id = 245,
            name = "Радиоточка",
            description = "– это удобные столы, мягкие диваны, много подушек. Это первый коворкинг, в котором расположена точка питания. Здесь студенты могут не только подготовиться к учебным занятиям, но и вкусно перекусить, пообедать. ",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 4f,
            images = listOf(Workspace.Image(12, ""), Workspace.Image(123, ""))
        ),
        Workspace(
            id = 12323,
            name = "Радиоточка",
            description = "– это удобные столы, мягкие диваны, много подушек. Это первый коворкинг, в котором расположена точка питания. Здесь студенты могут не только подготовиться к учебным занятиям, но и вкусно перекусить, пообедать. ",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 3.2f,
            images = listOf(
                Workspace.Image(12, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, "")
            )
        ),
        Workspace(
            id = 1234,
            name = "Радиоточка",
            description = "– это удобные столы, мягкие диваны, много подушек. Это первый коворкинг, в котором расположена точка питания. Здесь студенты могут не только подготовиться к учебным занятиям, но и вкусно перекусить, пообедать. ",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 3.2f,
            images = listOf(
                Workspace.Image(12, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, "")
            )
        )
    )

    private val _items: MutableStateFlow<List<Workspace>> = MutableStateFlow(hardData)
    val items: StateFlow<List<Workspace>> = _items.asStateFlow()

    override suspend fun getList(): Flow<List<Workspace>> {
        // TODO: _items.update { service.get() }
        return items
    }

    override suspend fun getById(id: Int): Workspace {
        return items.value.first { it.id == id }
    }
}