package com.na.coworking.domain.interfaces.bookings

import com.na.coworking.domain.entities.Booking
import kotlinx.coroutines.flow.Flow

interface BookingsDataSource {
    suspend fun getList(): Flow<List<Booking>>

    suspend fun cancelBooking(id: Int)

    suspend fun confirmBooking(bookingId: Int, code: Int)
}