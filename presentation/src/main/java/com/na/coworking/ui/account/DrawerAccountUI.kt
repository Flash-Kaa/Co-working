package com.na.coworking.ui.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.na.coworking.actions.AccountAction
import com.na.coworking.actions.AccountEvent
import com.na.coworking.appComponent
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.User
import com.na.coworking.navigation.Router
import com.na.coworking.ui.account.elements.Page
import kotlinx.coroutines.flow.Flow

@Composable
fun DrawerAccountUI(
    pageToOpen: Page,
    router: Router,
    padding: PaddingValues
) {
    val user = // TODO:, getUser() or authorizationUI() if empty
        User(1, "eeg", "trt", "re@we.ru", 0)

    val viewModel: AccountVM = viewModel(
        factory = LocalContext.current
            .appComponent
            .getAccountVMSubcomponent()
            .provideFactoryWrapper()
            .Factory(user, router)
    )

    DrawerAccountUI(
        user,
        pageToOpen,
        padding,
        viewModel.getBookings(),
        viewModel::getAction,
        viewModel::getEvent
    )
}

@Composable
private fun DrawerAccountUI(
    user: User,
    pageToOpen: Page,
    paddingValues: PaddingValues,
    bookings: Flow<List<Booking>>,
    getAction: (AccountAction) -> (() -> Unit),
    getEvent: (AccountEvent) -> (() -> Flow<LoadState>)
) {
    val page = remember { mutableStateOf(pageToOpen) }

    val state = bookings.collectAsState(initial = emptyList())

    AccountUI(user, page, paddingValues, state, getAction, getEvent)
}