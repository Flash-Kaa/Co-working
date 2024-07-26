package com.na.coworking.data.listofcowowking

import com.na.coworking.domain.entities.Amenity
import com.na.coworking.domain.entities.OperationMode
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject
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
            id = 12323,
            name = "Радиоточка",
            description = "Это для тестов - тут есть объекты, поэтому оно не крашится :)",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 3.2f,
            images = listOf(
                Workspace.Image(12, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, "")
            ),
            amenities = listOf(
                Amenity(1, Amenity.Template(1, "Доступ к Wi-Fi", "")),
                Amenity(1, Amenity.Template(1, "Бесплатное бронирование", "")),
                Amenity(1, Amenity.Template(1, "Комфортная обстановка", "")),
                Amenity(1, Amenity.Template(1, "Маркерная доска", "")),
            ),
            operationMode = listOf(
                OperationMode(0, "9:00", "20:30", 1),
                OperationMode(0, "9:00", "20:30", 3),
                OperationMode(0, "9:00", "20:30", 4),
                OperationMode(0, "10:00", "23:50", 5),
                OperationMode(0, "10:00", "23:50", 6),
            ),
            objects = listOf(
                WorkspaceObject(0, 0, 0, 100, 100, WorkspaceObject.Template(0, "", "", false)),
                WorkspaceObject(1, 100, 0, 100, 100, WorkspaceObject.Template(0, "", "", false)),
                WorkspaceObject(1, 0, 100, 100, 100, WorkspaceObject.Template(0, "", "", false)),
                WorkspaceObject(2, 100, 100, 200, 200, WorkspaceObject.Template(0, "", "", false)),
                WorkspaceObject(1, 310, 200, 110, 100, WorkspaceObject.Template(0, "", "", false)),
            )
        ),
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