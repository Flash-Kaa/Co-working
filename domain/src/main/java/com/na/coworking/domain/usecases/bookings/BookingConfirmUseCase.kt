package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookingConfirmUseCase(
    private val repository: BookingsRepository
) {
    private val _items: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.InProgress)
    val items: StateFlow<LoadState> = _items.asStateFlow()

    suspend operator fun invoke(id: Int, code: Int) {
        _items.update { LoadState.InProgress }
        try {
            // TODO: start with IO and supervisor JOb
            repository.confirmBooking(id, code)
            _items.update { LoadState.Successful }
        } catch (e: Throwable) {
            _items.update { LoadState.Error }
        }
    }

    fun getResult(): Flow<LoadState> = items
}