package com.na.coworking.data.di.bookings

import com.na.coworking.data.bookings.BookingsDSImpl
import com.na.coworking.data.di.BookingsScope
import com.na.coworking.data.di.bookings.binds.BookingsDataSourceBindModule
import com.na.coworking.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module(includes = [BookingsDataSourceBindModule::class])
internal class BookingsDataSourceModule {
    @Provides
    @BookingsScope
    fun provideDataSource(service: ApiService): BookingsDSImpl = BookingsDSImpl(service)
}