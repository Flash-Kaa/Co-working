package com.na.coworking.data.di.bookings

import com.na.coworking.data.bookings.BookingsDSImpl
import com.na.coworking.data.di.BookingsRepositoryScope
import com.na.coworking.data.di.bookings.binds.BookingsDataSourceBindModule
import dagger.Module
import dagger.Provides

@Module(includes = [BookingsDataSourceBindModule::class])
internal class BookingsDataSourceModule {
    @Provides
    @BookingsRepositoryScope
    fun provideDataSource(): BookingsDSImpl = BookingsDSImpl()
}