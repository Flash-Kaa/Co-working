package com.na.coworking.data.di.bookings.binds

import com.na.coworking.data.bookings.BookingsRepositoryImpl
import com.na.coworking.data.di.BookingsScope
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class BookingsRepositoryBindModule {
    @Binds
    @BookingsScope
    abstract fun bindBookingsRepositoryImplToInterface(
        repositoryImpl: BookingsRepositoryImpl
    ): BookingsRepository
}