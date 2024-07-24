package com.na.coworking.ui.coworking

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.na.coworking.domain.entities.Amenity
import com.na.coworking.domain.entities.OperationMode
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.ui.coworking.elements.Address
import com.na.coworking.ui.coworking.elements.Amenities
import com.na.coworking.ui.coworking.elements.CoworkingSchemeWithTitle
import com.na.coworking.ui.coworking.elements.Description
import com.na.coworking.ui.coworking.elements.ImagePager
import com.na.coworking.ui.coworking.elements.OperatingMode
import com.na.coworking.ui.global.TopAppBar

@Composable
fun CoworkingUI(
    coworking: MutableState<Workspace>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        item { ImagePager(coworking) }
        item { Description(coworking) }
        item { Amenities(coworking) }
        item { Address(coworking) }
        item { OperatingMode(coworking) }
        item { CoworkingSchemeWithTitle(coworking) }
        item { Spacer(modifier = Modifier.height(50.dp)) }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCoworkingUI() {
    val wot = WorkspaceObject.Template(0, "", "", false)

    val coworking = remember {
        mutableStateOf(
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
                ),
                amenities = listOf(
                    Amenity(1, Amenity.Template(1, "Доступ к Wi-Fi", "")),
                    Amenity(1, Amenity.Template(1, "Бесплатное бронирование", "")),
                    Amenity(1, Amenity.Template(1, "Комфортная обстановка", "")),
                    Amenity(1, Amenity.Template(1, "Маркерная доска", "")),
                ),
                operationMode = listOf(
                    OperationMode(0, "9-00", "20:30", 1),
                    OperationMode(0, "9-00", "20:30", 3),
                    OperationMode(0, "9-00", "20:30", 4),
                    OperationMode(0, "10-00", "23:50", 5),
                    OperationMode(0, "10-00", "23:50", 6),
                ),
                objects = listOf(
                    WorkspaceObject(0, 0, 0, 100, 100, wot),
                    WorkspaceObject(1, 100, 0, 100, 100, wot),
                    WorkspaceObject(1, 0, 100, 100, 100, wot),
                    WorkspaceObject(2, 100, 100, 200, 200, wot),
                    WorkspaceObject(1, 310, 200, 110, 100, wot),
                )
            )
        )
    }

    val sizeBarIsOpen = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(isOpen = sizeBarIsOpen, getAction = { {} }) },
        modifier = Modifier.fillMaxSize()
    ) {
        CoworkingUI(coworking = coworking, paddingValues = it)
    }
}