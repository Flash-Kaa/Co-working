package com.na.coworking.actions

sealed class AccountAction {
    // Navigation
    data object OnExit: AccountAction()
    data class OnConfirmBooking(val bookingId: Int): AccountAction()
    data class OnCancelBooking(val bookingId: Int): AccountAction()
}