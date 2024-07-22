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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun getEvent(event: AccountEvent) {
        when (event) {
            is AccountEvent.OnCancelBooking -> cancelBooking(event)
            is AccountEvent.OnConfirmBooking -> confirmBooking(event)
        }
    }

    private fun cancelBooking(event: AccountEvent.OnCancelBooking) {
        viewModelScope.launch {
            launch {
                bookingCancel(event.bookingId)
            }

            launch {
                val flow = bookingCancel.getResult()
                executeEventFromFlow(flow, event)
            }
        }
    }

    private fun confirmBooking(event: AccountEvent.OnConfirmBooking) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                bookingConfirm(event.bookingId, event.code)
            }

            launch {
                val flow = bookingConfirm.getResult()
                executeEventFromFlow(flow, event)
            }
        }

    }

    private fun exitAccount() {}

    private suspend fun executeEventFromFlow(flow: Flow<LoadState>, event: AccountEvent) {
        flow.collectLatest {
            withContext(Dispatchers.Main) {
                when (it) {
                    LoadState.Successful -> event.onSuccess()
                    LoadState.Progress -> event.onProgress()
                    LoadState.Error -> event.onError()
                    LoadState.None -> {}
                }
            }
        }
    }

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