package com.na.coworking.di.modules.account

import com.na.coworking.data.di.BookingsScope
import com.na.coworking.data.di.bookings.BookingsRepositoryModule
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetBookingsUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [BookingsRepositoryModule::class])
internal class BookingsUseCasesModule {
    @Provides
    @BookingsScope
    fun provideGetBookingsUseCase(
        repository: BookingsRepository
    ): GetBookingsUseCase = GetBookingsUseCase(repository)

    @Provides
    @BookingsScope
    fun provideCancelUseCase(
        repository: BookingsRepository
    ): BookingCancelUseCase = BookingCancelUseCase(repository)

    @Provides
    @BookingsScope
    fun provideConfirmUseCase(
        repository: BookingsRepository
    ): BookingConfirmUseCase = BookingConfirmUseCase(repository)
}