package com.na.coworking.data.bookings

import com.na.coworking.data.network.ApiService
import com.na.coworking.data.network.entities.ReservationsNetEntity
import com.na.coworking.data.network.entities.ReservationsNetEntity.ReservationsNetEntityItem.Companion.toReservation
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.Location
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

internal class BookingsDSImpl(
    private val service: ApiService
) : BookingsDataSource {
    override suspend fun getCoworkingBookings(coworkingId: Int): Flow<List<CoworkingBooking>> {
        return flow {
            emit(
                service.getListOfWorkspaceBookings(coworkingId)
                    .map { it.toCoworkingBooking() }
            )
        }
    }

    override suspend fun getUserBookings(userId: Int): Flow<List<Booking>> {
        return flow {
            emit(
                service.getListOfUserBookings(userId)
                    .map {
                        val workspace = service.getWorkspaceById(it.idWorkspace)
                        it.toBooking(workspace)
                    }
            )
        }
    }

    override suspend fun cancelBooking(id: Int) {
        service.cancelBooking(id)
    }

    override suspend fun confirmBooking(bookingId: Int, location: Location) {
        service.confirmBooking(bookingId, location)
    }

    override suspend fun addBooking(bookingData: CoworkingBooking) {
        service.sendBooking(bookingData.toReservation())
    }
}