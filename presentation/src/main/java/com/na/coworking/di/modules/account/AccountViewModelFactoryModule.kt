package com.na.coworking.di.modules.account

import android.content.Context
import com.na.coworking.appComponent
import com.na.coworking.data.di.BookingsRepositoryScope
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetBookingsUseCase
import com.na.coworking.ui.account.AccountVM
import dagger.Module
import dagger.Provides

@Module(includes = [BookingsUseCasesModule::class])
internal class AccountViewModelFactoryModule {
    @Provides
    @BookingsRepositoryScope
    fun provideViewModel(
        getBookingsUseCase: GetBookingsUseCase,
        cancelUseCase: BookingCancelUseCase,
        confirmUseCase: BookingConfirmUseCase,
        context: Context
    ): AccountVM.FactoryWrapperWithUseCases = AccountVM.FactoryWrapperWithUseCases(
        bookings = getBookingsUseCase,
        bookingConfirm = confirmUseCase,
        bookingCancel = cancelUseCase,
        logout = context.appComponent.getTokenUseCasesSubcomponent().provideLogoutUseCase()
    )
}