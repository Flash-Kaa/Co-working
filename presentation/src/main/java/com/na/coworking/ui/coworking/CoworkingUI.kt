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
import com.na.coworking.actions.CoworkingEvent
import com.na.coworking.domain.entities.Amenity
import com.na.coworking.domain.entities.OperationMode
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.ui.coworking.elements.Address
import com.na.coworking.ui.coworking.elements.Amenities
import com.na.coworking.ui.coworking.elements.CoworkingSchemeWithTitleAndButton
import com.na.coworking.ui.coworking.elements.Description
import com.na.coworking.ui.coworking.elements.ImagePager
import com.na.coworking.ui.coworking.elements.OperatingMode
import com.na.coworking.ui.coworking.elements.bookingdialog.BookingDialogUI
import com.na.coworking.ui.global.TopAppBar

@Composable
internal fun CoworkingUI(
    coworking: MutableState<Workspace>,
    paddingValues: PaddingValues,
    timesRangesToBooking: List<String>,
    daysToBooking: List<String>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    getTimes: (String, String) -> List<List<Pair<String, String>>>,
    getEvent: (CoworkingEvent) -> Unit
) {
    val bookingDialogIsOpen = remember { mutableStateOf(false) }

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
        item { CoworkingSchemeWithTitleAndButton(coworking, bookingDialogIsOpen) }
        item { Spacer(modifier = Modifier.height(50.dp)) }
    }

    if (bookingDialogIsOpen.value) {
        val state = remember { mutableStateOf(BookingStateUI()) }

        BookingDialogUI(
            state = state,
            timesRangesToBooking = timesRangesToBooking,
            daysToBooking = daysToBooking,
            getTemplates = getTemplates,
            getTimes = getTimes,
            onDismiss = { bookingDialogIsOpen.value = false },
            getEvent = getEvent
        )
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
                    WorkspaceObject(0, 0, 0, 100, 100, wot.copy(category = "red")),
                    WorkspaceObject(1, 100, 0, 100, 100, wot.copy(category = "blue")),
                    WorkspaceObject(1, 0, 100, 100, 100, wot.copy(category = "blue")),
                    WorkspaceObject(2, 100, 100, 200, 200, wot.copy(category = "green")),
                    WorkspaceObject(1, 310, 200, 110, 100, wot.copy(category = "blue")),
                )
            )
        )
    }

    val sizeBarIsOpen = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(isOpen = sizeBarIsOpen, getAction = { {} }) },
        modifier = Modifier.fillMaxSize()
    ) {
        CoworkingUI(
            coworking = coworking, paddingValues = it,
            getTemplates = { coworking.value.objects },
            timesRangesToBooking = listOf("45 мин", "1 час", "2 часа"),
            daysToBooking = listOf(
                "25.07.2024",
                "26.07.2024",
                "27.07.2024",
                "25.07.2024",
                "26.07.2024",
                "27.07.2024"
            ),
            getTimes = { _, _ ->
                listOf(
                    listOf("12:00" to "13:00", "13:00" to "14:00", "14:00" to "15:00"),
                    listOf("15:00" to "16:00", "16:00" to "17:00", "17:00" to "18:00"),
                    listOf("18:00" to "19:00", "19:00" to "20:00", "20:00" to "21:00"),
                    listOf("21:00" to "22:00"),
                )
            },
            getEvent = {
                it.onError()
            }
        )
    }
}