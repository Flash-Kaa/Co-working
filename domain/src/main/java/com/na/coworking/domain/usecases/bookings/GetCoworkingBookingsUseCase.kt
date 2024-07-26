package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import com.na.coworking.domain.usecases.runWithSupervisorInBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GetCoworkingBookingsUseCase(
    private val repository: BookingsRepository
) {
    private val _items = MutableStateFlow<List<CoworkingBooking>>(emptyList())
    val items = _items.asStateFlow()

    suspend operator fun invoke(coworkingId: Int) {
        runWithSupervisorInBackground {
            repository.getCoworkingBookings(coworkingId).collect { list ->
                _items.update { list }
            }
        }
    }
}