package com.na.coworking.di.components

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.di.modules.mainpage.MainUseCasesModule
import com.na.coworking.domain.usecases.bookings.GetFreeTimesUseCase
import com.na.coworking.domain.usecases.bookings.GetTemplatesUseCase
import com.na.coworking.domain.usecases.listofcoworking.GetCoworkingByIdUseCase
import com.na.coworking.domain.usecases.listofcoworking.GetListOfCoworkingUseCase
import dagger.Subcomponent

@Subcomponent(modules = [MainUseCasesModule::class])
@ListOfCoworkingScope
internal interface CoworkingUseCaseSubcomponent {
    fun provideCoworkingViewModelComponent(): CoworkingViewModelFactorySubcomponent

    fun provideGetUseCase(): GetListOfCoworkingUseCase

    fun provideGetCoworkingByIdUseCase(): GetCoworkingByIdUseCase

    fun provideGetFreeTimesUseCase(): GetFreeTimesUseCase

    fun provideGetTemplatesUseCase(): GetTemplatesUseCase
}