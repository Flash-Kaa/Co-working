package com.na.coworking.di.modules.bookings

import com.na.coworking.data.di.BookingsScope
import com.na.coworking.data.di.bookings.BookingsRepositoryModule
import com.na.coworking.domain.interfaces.bookings.BookingsRepository
import com.na.coworking.domain.usecases.bookings.AddBookingUseCase
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetCoworkingBookingsUseCase
import com.na.coworking.domain.usecases.bookings.GetUserBookingsUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [BookingsRepositoryModule::class])
internal class BookingsUseCasesModule {
    @Provides
    @BookingsScope
    fun provideGetCoworkingBookingsUseCase(
        repository: BookingsRepository
    ) = GetCoworkingBookingsUseCase(repository)

    @Provides
    @BookingsScope
    fun provideGetBookingsUseCase(
        repository: BookingsRepository
    ): GetUserBookingsUseCase = GetUserBookingsUseCase(repository)

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

    @Provides
    @BookingsScope
    fun provideAddBookingUseCase(
        repository: BookingsRepository
    ): AddBookingUseCase = AddBookingUseCase(repository)
}