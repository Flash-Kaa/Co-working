package com.na.coworking.di.modules.authorization

import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.data.di.authorization.AuthorizationRepositoryModule
import com.na.coworking.domain.interfaces.authorization.AuthorizationRepository
import com.na.coworking.domain.usecases.authorization.AuthorizeDataUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizationRepositoryModule::class])
internal class AuthorizeDataUseCaseModule {
    @Provides
    @AuthRepositoryScope
    fun provideAuthorizeUseCase(repository: AuthorizationRepository) =
        AuthorizeDataUseCase(repository)
}