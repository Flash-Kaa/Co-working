package com.na.coworking.data.di.bookings

import com.na.coworking.data.bookings.BookingsRepositoryImpl
import com.na.coworking.data.di.BookingsScope
import com.na.coworking.data.di.bookings.binds.BookingsRepositoryBindModule
import com.na.coworking.domain.interfaces.bookings.BookingsDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [BookingsRepositoryBindModule::class, BookingsDataSourceModule::class])
class BookingsRepositoryModule {
    @Provides
    @BookingsScope
    fun provideRepository(
        dataSource: BookingsDataSource
    ): BookingsRepositoryImpl = BookingsRepositoryImpl(dataSource)
}