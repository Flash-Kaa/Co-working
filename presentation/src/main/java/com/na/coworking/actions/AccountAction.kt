package com.na.coworking.actions

sealed class AccountAction {
    // Navigation
    data object OnExit: AccountAction()

    // Event
    data class OnConfirmBooking(val bookingId: Int, val code: Int): AccountAction()
    data class OnCancelBooking(val bookingId: Int): AccountAction()
}