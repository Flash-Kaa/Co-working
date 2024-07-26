package com.na.coworking.di.components

import com.na.coworking.data.di.BookingsScope
import com.na.coworking.di.modules.bookings.BookingsUseCasesModule
import com.na.coworking.domain.usecases.bookings.AddBookingUseCase
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetCoworkingBookingsUseCase
import com.na.coworking.domain.usecases.bookings.GetUserBookingsUseCase
import dagger.Subcomponent

@BookingsScope
@Subcomponent(modules = [BookingsUseCasesModule::class])
internal interface BookingsUseCaseSubcomponent {
    fun provideAccountViewModelComponent(): AccountViewModelSubcomponent

    fun provideCoworkingUseCaseComponent(): CoworkingUseCaseSubcomponent

    fun provideAddBookingUseCase(): AddBookingUseCase

    fun provideConfirmBookingUseCase(): BookingConfirmUseCase

    fun provideCancelBookingUseCase(): BookingCancelUseCase

    fun provideGetBookingsUseCase(): GetUserBookingsUseCase

    fun provideGetCoworkingUseCase(): GetCoworkingBookingsUseCase
}