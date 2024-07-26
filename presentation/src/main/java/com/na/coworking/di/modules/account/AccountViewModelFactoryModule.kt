package com.na.coworking.di.modules.account

import com.na.coworking.domain.usecases.account.GetUserUseCase
import com.na.coworking.domain.usecases.authorization.LogoutUseCase
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetUserBookingsUseCase
import com.na.coworking.ui.account.AccountVM
import dagger.Module
import dagger.Provides

@Module
internal class AccountViewModelFactoryModule {
    @Provides
    fun provideViewModel(
        getUserBookingsUseCase: GetUserBookingsUseCase,
        cancelUseCase: BookingCancelUseCase,
        confirmUseCase: BookingConfirmUseCase,
        logoutUseCase: LogoutUseCase,
        getUserUseCase: GetUserUseCase
    ): AccountVM.FactoryWrapperWithUseCases = AccountVM.FactoryWrapperWithUseCases(
        bookings = getUserBookingsUseCase,
        bookingConfirm = confirmUseCase,
        bookingCancel = cancelUseCase,
        logout = logoutUseCase,
        getUserUseCase = getUserUseCase
    )
}