package com.na.coworking.di.modules.authorization

import com.na.coworking.data.di.AuthorizationScope
import com.na.coworking.domain.usecases.authorization.AuthorizeDataUseCase
import com.na.coworking.domain.usecases.authorization.LoginUseCase
import com.na.coworking.domain.usecases.authorization.UpdateTokenUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizeDataUseCaseModule::class])
internal class LoginUseCaseModule {
    @Provides
    @AuthorizationScope
    fun provideLoginUseCase(
        updateTokenUseCase: UpdateTokenUseCase,
        authorizeUseCase: AuthorizeDataUseCase
    ) = LoginUseCase(
        updateTokenUseCase = updateTokenUseCase,
        authorizeUseCase = authorizeUseCase
    )
}