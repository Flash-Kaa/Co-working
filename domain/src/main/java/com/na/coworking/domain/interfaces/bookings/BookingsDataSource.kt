package com.na.coworking.domain.interfaces.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.CoworkingBooking
import kotlinx.coroutines.flow.Flow

interface BookingsDataSource {
    suspend fun getCoworkingBookings(coworkingId: Int): Flow<List<CoworkingBooking>>

    suspend fun getUserBookings(): Flow<List<Booking>>

    suspend fun cancelBooking(id: Int)

    suspend fun confirmBooking(bookingId: Int, code: Int)

    suspend fun addBooking(bookingData: CoworkingBooking)
}