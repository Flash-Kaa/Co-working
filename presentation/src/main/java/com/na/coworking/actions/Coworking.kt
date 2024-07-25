package com.na.coworking.actions

import com.na.coworking.ui.coworking.BookingStateUI

internal sealed class CoworkingEvent(
    open val onError: () -> Unit,
    open val onSuccess: () -> Unit,
    open val onProgress: () -> Unit
) {
    data class Booking(
        val bookingData: BookingStateUI,
        override val onError: () -> Unit = { },
        override val onSuccess: () -> Unit = { },
        override val onProgress: () -> Unit = { }
    ): CoworkingEvent(onError, onSuccess, onProgress)
}