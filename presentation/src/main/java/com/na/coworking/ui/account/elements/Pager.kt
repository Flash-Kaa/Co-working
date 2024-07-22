package com.na.coworking.ui.account.elements

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.User
import kotlinx.coroutines.flow.Flow


fun LazyListScope.pager(
    user: User,
    page: MutableState<Page>,
    bookings: State<List<Booking>>,

    getEvent: (AccountEvent) -> (() -> Flow<LoadState>)
) {
    when (page.value) {
        Page.Booking -> bookings(bookings, getEvent)
        Page.Profile -> item { Profile(user) }
    }
}