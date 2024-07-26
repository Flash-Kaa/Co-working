package com.na.coworking.di.modules.coworking

import com.na.coworking.domain.usecases.bookings.AddBookingUseCase
import com.na.coworking.domain.usecases.bookings.GetCoworkingBookingsUseCase
import com.na.coworking.domain.usecases.bookings.GetFreeTimesUseCase
import com.na.coworking.domain.usecases.bookings.GetTemplatesUseCase
import com.na.coworking.domain.usecases.listofcoworking.GetCoworkingByIdUseCase
import com.na.coworking.ui.coworking.CoworkingVM
import dagger.Module
import dagger.Provides

@Module
internal class CoworkingViewModelFactoryModule {
    @Provides
    fun provideCoworkingVM(
        getCoworkingByIdUseCase: GetCoworkingByIdUseCase,
        getCoworkingBookingsUseCase: GetCoworkingBookingsUseCase,
        addBookingUseCase: AddBookingUseCase,
        getFreeTimesUseCase: GetFreeTimesUseCase,
        getTemplatesUseCase: GetTemplatesUseCase
    ) = CoworkingVM.FactoryWrapperWithUseCases(
        getCoworkingByIdUseCase = getCoworkingByIdUseCase,
        getCoworkingBookingsUseCase = getCoworkingBookingsUseCase,
        addBookingUseCase = addBookingUseCase,
        getFreeTimesUseCase = getFreeTimesUseCase,
        getTemplatesUseCase = getTemplatesUseCase
    )
}