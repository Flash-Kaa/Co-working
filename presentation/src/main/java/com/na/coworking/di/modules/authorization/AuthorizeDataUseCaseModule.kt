package com.na.coworking.di.modules.authorization

import com.na.coworking.data.di.AuthorizationScope
import com.na.coworking.data.di.authorization.AuthorizationRepositoryModule
import com.na.coworking.domain.interfaces.authorization.AuthorizationRepository
import com.na.coworking.domain.usecases.authorization.AuthorizeDataUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizationRepositoryModule::class])
internal class AuthorizeDataUseCaseModule {
    @Provides
    @AuthorizationScope
    fun provideAuthorizeUseCase(repository: AuthorizationRepository) =
        AuthorizeDataUseCase(repository)
}