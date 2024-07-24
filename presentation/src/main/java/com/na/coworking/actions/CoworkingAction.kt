package com.na.coworking.actions

internal sealed class CoworkingAction {
    data object OpenBooking : CoworkingAction()
}