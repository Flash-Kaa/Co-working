package com.na.coworking.actions

sealed class AccountAction {
    data object OnExit : AccountAction()
}

sealed class AccountEvent {
    data class OnConfirmBooking(val bookingId: Int, val code: Int) : AccountEvent()
    data class OnCancelBooking(val bookingId: Int) : AccountEvent()
}