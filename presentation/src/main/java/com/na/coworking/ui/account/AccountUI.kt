package com.na.coworking.ui.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.na.coworking.actions.AccountAction
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.Booking
import com.na.coworking.ui.account.elements.Page
import com.na.coworking.ui.account.elements.PagerTitle
import com.na.coworking.ui.account.elements.UserTitleBg
import com.na.coworking.ui.account.elements.pager
import com.na.coworking.ui.global.TopAppBar
import kotlinx.coroutines.flow.flow

@Composable
internal fun AccountUI(
    user: MutableState<UserStateUI>,
    page: MutableState<Page>,
    paddingValues: PaddingValues,
    bookings: State<List<Booking>>,
    getAction: (AccountAction) -> (() -> Unit),
    onEvent: (AccountEvent) -> Unit
) {
    LazyColumn(
        contentPadding = paddingValues
    ) {
        item { UserTitleBg(user, getAction) }
        item { PagerTitle(page) }
        pager(user, page, bookings, onEvent)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAccountUI() {
    val user = remember {
        mutableStateOf(
            UserStateUI(
                id = 0,
                email = "flashkaa@forever.com",
                firstName = "egor",
                secondName = "unknown",
                accessLevel = 0
            )
        )
    }

    val page = remember {
        mutableStateOf(Page.Booking)
    }

    val list = listOf(
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

    val state = flow { emit(list) }.collectAsState(initial = emptyList())

    val sizeBarIsOpen = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = { TopAppBar(sizeBarIsOpen, { {} }) },
        modifier = Modifier.fillMaxSize(),
    ) {
        AccountUI(user, page, it, state, { {} }, { })
    }
}