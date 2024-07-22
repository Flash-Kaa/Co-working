package com.na.coworking.di.modules.account

import com.na.coworking.data.di.BookingsRepositoryScope
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
    @BookingsRepositoryScope
    fun provideGetBookingsUseCase(
        repository: BookingsRepository
    ): GetBookingsUseCase = GetBookingsUseCase(repository)

    @Provides
    @BookingsRepositoryScope
    fun provideCancelUseCase(
        repository: BookingsRepository
    ): BookingCancelUseCase = BookingCancelUseCase(repository)

    @Provides
    @BookingsRepositoryScope
    fun provideConfirmUseCase(
        repository: BookingsRepository
    ): BookingConfirmUseCase = BookingConfirmUseCase(repository)
}