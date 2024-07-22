package com.na.coworking.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.na.coworking.actions.AccountAction
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.User
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetBookingsUseCase
import com.na.coworking.navigation.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal class AccountVM(
    private val user: User,
    private val router: Router,

    private val bookingConfirm: BookingConfirmUseCase,
    private val bookingCancel: BookingCancelUseCase,
    private val bookings: GetBookingsUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            bookings.fetch()
        }
    }

    fun getBookings(): Flow<List<Booking>> = bookings.invoke()

    fun getAction(action: AccountAction): () -> Unit {
        return when (action) {
            is AccountAction.OnExit -> ::exitAccount
        }
    }

    fun getEvent(event: AccountEvent): () -> Flow<LoadState> {
        return when (event) {
            is AccountEvent.OnCancelBooking -> cancelBooking(event.bookingId)
            is AccountEvent.OnConfirmBooking -> confirmBooking(event.bookingId, event.code)
        }
    }

    private fun cancelBooking(bookingId: Int): () -> Flow<LoadState> = {
        viewModelScope.launch {
            bookingCancel(bookingId)
        }

        bookingCancel.getResult()
    }

    private fun confirmBooking(bookingId: Int, code: Int): () -> Flow<LoadState> = {
        viewModelScope.launch {
            bookingConfirm(bookingId, code)
        }

        bookingConfirm.getResult()
    }

    private fun exitAccount() {}

    class FactoryWrapperWithUseCases(
        private val bookingConfirm: BookingConfirmUseCase,
        private val bookingCancel: BookingCancelUseCase,
        private val bookings: GetBookingsUseCase
    ) {
        inner class Factory(
            private val user: User,
            private val router: Router
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AccountVM(
                    user = user,
                    router = router,
                    bookingConfirm = bookingConfirm,
                    bookingCancel = bookingCancel,
                    bookings = bookings
                ) as T
            }
        }
    }
}