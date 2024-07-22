package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import com.na.coworking.domain.usecases.runWithSupervisorInBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookingCancelUseCase(
    private val repository: BookingsRepository
) {
    private val _items: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Progress)
    val items: StateFlow<LoadState> = _items.asStateFlow()

    suspend operator fun invoke(id: Int) {
        _items.update { LoadState.Progress }

        runWithSupervisorInBackground(
            onErrorAction = { _items.update { LoadState.Error } }
        ) {
            repository.cancelBooking(id)
            _items.update { LoadState.Successful }
        }
    }

    fun getResult(): Flow<LoadState> = items
}