package com.na.coworking.data.bookings

import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import kotlinx.coroutines.flow.Flow

class BookingsRepositoryImpl(
    private val dataSource: BookingsDataSource
) : BookingsRepository {
    override suspend fun getList(): Flow<List<Booking>> {
        return dataSource.getList()
    }

    override suspend fun cancelBooking(id: Int) {
        dataSource.cancelBooking(id)
    }

    override suspend fun confirmBooking(bookingId: Int, code: Int) {
        dataSource.confirmBooking(bookingId, code)
    }
}