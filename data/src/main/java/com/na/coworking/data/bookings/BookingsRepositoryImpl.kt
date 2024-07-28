package com.na.coworking.data.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.Location
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import kotlinx.coroutines.flow.Flow

class BookingsRepositoryImpl(
    private val dataSource: BookingsDataSource
) : BookingsRepository {
    override suspend fun getCoworkingBookings(coworkingId: Int): Flow<List<CoworkingBooking>> {
        return dataSource.getCoworkingBookings(coworkingId)
    }

    override suspend fun getUserBookings(userId: Int): Flow<List<Booking>> {
        return dataSource.getUserBookings(userId)
    }

    override suspend fun cancelBooking(id: Int) {
        dataSource.cancelBooking(id)
    }

    override suspend fun confirmBooking(bookingId: Int, location: Location) {
        dataSource.confirmBooking(bookingId, location)
    }

    override suspend fun addBooking(bookingData: CoworkingBooking) {
        dataSource.addBooking(bookingData)
    }
}