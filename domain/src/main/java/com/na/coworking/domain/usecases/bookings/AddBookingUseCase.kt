package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import com.na.coworking.domain.usecases.runWithSupervisorInBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddBookingUseCase(
    private val repository: BookingsRepository
) {
    private val _state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.None)
    val state: StateFlow<LoadState> = _state.asStateFlow()

    suspend operator fun invoke(bookingData: CoworkingBooking) {
        _state.update { LoadState.Progress }

        runWithSupervisorInBackground(
            onErrorAction = { _state.update { LoadState.Error } }
        ) {
            repository.addBooking(bookingData)
            _state.update { LoadState.Successful }
        }
    }
}