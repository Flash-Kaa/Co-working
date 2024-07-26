package com.na.coworking.data.di.bookings.binds

import com.na.coworking.data.bookings.BookingsDSImpl
import com.na.coworking.data.di.BookingsScope
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class BookingsDataSourceBindModule {
    @Binds
    @BookingsScope
    abstract fun bindBookingsDataSourceImplToInterface(
        dataSource: BookingsDSImpl
    ): BookingsDataSource
}