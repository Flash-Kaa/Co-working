package com.na.coworking.data.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class BookingsDSImpl(
    // TODO: private val service: BookingService
) : BookingsDataSource {
    private var list = listOf(
        Booking(
            id = 0,
            timeStart = "12:00",
            timeEnd = "13:00",
            date = "09.07.2024",
            image = "",
            isConfirmed = false,
            name = "радиоточка"
        ),
        Booking(
            id = 1,
            timeStart = "18:30",
            timeEnd = "19:00",
            date = "14.08.2024",
            image = "",
            isConfirmed = false,
            name = "коворкинг"
        ),
        Booking(
            id = 2,
            timeStart = "14:00",
            timeEnd = "14:20",
            date = "01.07.2024",
            image = "",
            isConfirmed = false,
            name = "р-044"
        ),
    )

    private val _items = MutableStateFlow(list)
    private val items = _items.asStateFlow()

    override suspend fun getList(): Flow<List<Booking>> = items

    override suspend fun cancelBooking(id: Int) {
        list = list.filter { it.id != id }
        _items.update { list }
    }

    override suspend fun confirmBooking(bookingId: Int, code: Int) {
        if (code != 1331) {
            throw IllegalArgumentException("unexpected code")
        }

        list = list.map {
            if (it.id == bookingId) it.copy(isConfirmed = true) else it
        }

        _items.update { list }
    }
}