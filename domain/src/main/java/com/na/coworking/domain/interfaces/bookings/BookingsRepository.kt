package com.na.coworking.domain.interfaces.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.Location
import kotlinx.coroutines.flow.Flow

interface BookingsRepository {
    suspend fun getCoworkingBookings(coworkingId: Int): Flow<List<CoworkingBooking>>

    suspend fun getUserBookings(userId: Int): Flow<List<Booking>>

    suspend fun cancelBooking(id: Int)

    suspend fun confirmBooking(bookingId: Int, location: Location)

    suspend fun addBooking(bookingData: CoworkingBooking)
}