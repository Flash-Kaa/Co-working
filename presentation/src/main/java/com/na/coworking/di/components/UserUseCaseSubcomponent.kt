package com.na.coworking.di.components

import com.na.coworking.data.di.UserScope
import com.na.coworking.di.modules.account.UserUseCaseModule
import com.na.coworking.domain.usecases.account.GetUserUseCase
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserUseCaseModule::class])
internal interface UserUseCaseSubcomponent {
    fun provideBookingsUseCase(): BookingsUseCaseSubcomponent

    fun provideGetUserUseCase(): GetUserUseCase
}