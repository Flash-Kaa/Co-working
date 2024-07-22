package com.na.coworking.actions

sealed class AccountAction {
    data object OnExit : AccountAction()
}

sealed class AccountEvent(
    open val onError: () -> Unit,
    open val onSuccess: () -> Unit,
    open val onProgress: () -> Unit
) {
    data class OnConfirmBooking(
        val bookingId: Int,
        val code: Int,
        override val onError: () -> Unit = { },
        override val onSuccess: () -> Unit = { },
        override val onProgress: () -> Unit = { }
    ) : AccountEvent(onError, onSuccess, onProgress)

    data class OnCancelBooking(
        val bookingId: Int,
        override val onError: () -> Unit = { },
        override val onSuccess: () -> Unit = { },
        override val onProgress: () -> Unit = { }
    ) : AccountEvent(onError, onSuccess, onProgress)
}