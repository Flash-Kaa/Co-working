package com.na.coworking.ui.coworking

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.na.coworking.actions.CoworkingEvent
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.ui.coworking.elements.Address
import com.na.coworking.ui.coworking.elements.Amenities
import com.na.coworking.ui.coworking.elements.CoworkingSchemeWithTitleAndButton
import com.na.coworking.ui.coworking.elements.Description
import com.na.coworking.ui.coworking.elements.ImagePager
import com.na.coworking.ui.coworking.elements.OperatingMode
import com.na.coworking.ui.coworking.elements.bookingdialog.BookingDialogUI

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