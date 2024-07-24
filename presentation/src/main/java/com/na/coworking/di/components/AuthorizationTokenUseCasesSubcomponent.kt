package com.na.coworking.di.components

import com.na.coworking.data.di.TokenRepositoryScope
import com.na.coworking.di.modules.authorization.AuthorizationTokenUseCasesModule
import com.na.coworking.domain.usecases.authorization.GetTokenUseCase
import com.na.coworking.domain.usecases.authorization.HasLoginUseCase
import com.na.coworking.domain.usecases.authorization.LogoutUseCase
import com.na.coworking.domain.usecases.authorization.UpdateTokenUseCase
import dagger.Subcomponent

@TokenRepositoryScope
@Subcomponent(modules = [AuthorizationTokenUseCasesModule::class])
internal interface AuthorizationTokenUseCasesSubcomponent {
    fun provideGetTokenUseCase(): GetTokenUseCase

    fun provideUpdateTokenUseCase(): UpdateTokenUseCase

    fun provideLogoutUseCase(): LogoutUseCase

    fun provideHasLoginUseCase(): HasLoginUseCase
}