package com.na.coworking.ui.account.elements

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.User


internal fun LazyListScope.pager(
    user: User,
    page: MutableState<Page>,
    bookings: State<List<Booking>>,

    onEvent: (AccountEvent) -> Unit
) {
    when (page.value) {
        Page.Booking -> bookings(bookings, onEvent)
        Page.Profile -> item { Profile(user) }
    }
}