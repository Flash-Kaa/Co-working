package com.na.coworking.actions

sealed class AccountAction {
    // Navigation
    data object OnExit: AccountAction()
    data class OnConfirmBooking(val bookingId: Int): AccountAction()

    data object UserBooking: AccountAction()
    data object UserInfo: AccountAction()
}