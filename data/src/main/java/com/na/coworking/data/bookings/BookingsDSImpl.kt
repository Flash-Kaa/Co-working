package com.na.coworking.data.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class BookingsDSImpl(
    // TODO: private val service: BookingService
) : BookingsDataSource {
    private var userBookings = listOf(
        Booking(
            id = 0,
            timeStart = "12:00",
            timeEnd = "13:00",
            date = "2024-07-27",
            image = "",
            isConfirmed = false,
            name = "радиоточка"
        ),
        Booking(
            id = 1,
            timeStart = "18:30",
            timeEnd = "19:00",
            date = "2024-07-27",
            image = "",
            isConfirmed = false,
            name = "коворкинг"
        ),
        Booking(
            id = 2,
            timeStart = "14:00",
            timeEnd = "14:20",
            date = "2024-07-27",
            image = "",
            isConfirmed = false,
            name = "р-044"
        ),
    )

    private var allBookings = listOf(
        CoworkingBooking(
            idObject = 1,
            idWorkspace = 12323,
            date = "2024-07-27",
            isConfirmed = false,
            timeStart = "12:00",
            timeEnd = "13:00"
        ),
        CoworkingBooking(
            idObject = 1,
            idWorkspace = 12323,
            date = "2024-07-27",
            isConfirmed = false,
            timeStart = "18:30",
            timeEnd = "19:20"
        ),
        CoworkingBooking(
            idObject = 1,
            idWorkspace = 12323,
            date = "2024-07-27",
            isConfirmed = false,
            timeStart = "14:00",
            timeEnd = "14:20"
        ),
    )

    private val _userItems = MutableStateFlow(userBookings)
    private val userItems = _userItems.asStateFlow()

    private val _allItems = MutableStateFlow(allBookings)
    private val allItems = _allItems.asStateFlow()

    override suspend fun getCoworkingBookings(coworkingId: Int): Flow<List<CoworkingBooking>> =
        allItems

    override suspend fun getUserBookings(): Flow<List<Booking>> = userItems

    override suspend fun cancelBooking(id: Int) {
        userBookings = userBookings.filter { it.id != id }

        _userItems.update { userBookings }
    }

    override suspend fun confirmBooking(bookingId: Int, code: Int) {
        userBookings = userBookings.map {
            if (it.id == bookingId) it.copy(isConfirmed = true) else it
        }

        _userItems.update { userBookings }
    }

    override suspend fun addBooking(bookingData: CoworkingBooking) {
        // TODO
    }
}