package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import com.na.coworking.domain.usecases.runWithSupervisorInBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GetBookingsUseCase(
    private val repository: BookingsRepository
) {
    private val _items = MutableStateFlow<List<Booking>>(emptyList())
    val items = _items.asStateFlow()

    suspend operator fun invoke() {
        runWithSupervisorInBackground {
            repository.getList().collect { list ->
                _items.update { list }
            }
        }
    }
}