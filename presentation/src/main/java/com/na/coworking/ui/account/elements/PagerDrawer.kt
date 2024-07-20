package com.na.coworking.ui.account.elements

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.na.coworking.actions.AccountAction
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.User


fun LazyListScope.pagerDrawer(
    user: User,
    page: MutableState<Page>,

    getAction: (AccountAction) -> (() -> Unit)
) {
    //val bookings: List<Booking> = emptyList() //TODO: add vm
    val bookings: List<Booking> = listOf(
        Booking(
            id = 0,
            timeStart = "12:00",
            timeEnd = "13:00",
            date = "09.07.2024",
            image = "",
            isConfirmed = false,
            name = "радиоточка"
        ),
        Booking(
            id = 1,
            timeStart = "18:30",
            timeEnd = "19:00",
            date = "14.08.2024",
            image = "",
            isConfirmed = false,
            name = "коворкинг"
        ),
        Booking(
            id = 2,
            timeStart = "14:00",
            timeEnd = "14:20",
            date = "01.07.2024",
            image = "",
            isConfirmed = false,
            name = "р-044"
        ),
    )

    pager(user, page, bookings, getAction)
}

private fun LazyListScope.pager(
    user: User,
    page: MutableState<Page>,
    bookings: List<Booking>,
    getAction: (AccountAction) -> (() -> Unit)
) {
    when (page.value) {
        Page.Booking -> bookings(bookings, getAction)
        Page.Profile -> item { Profile(user) }
    }
}