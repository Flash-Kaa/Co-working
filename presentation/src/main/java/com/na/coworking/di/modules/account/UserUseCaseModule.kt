package com.na.coworking.di.modules.account

import com.na.coworking.data.di.UserScope
import com.na.coworking.domain.interfaces.JsonMapper
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import com.na.coworking.domain.usecases.account.GetUserUseCase
import dagger.Module
import dagger.Provides

@Module
internal class UserUseCaseModule {
    @Provides
    @UserScope
    fun provideGetUserUseCase(repository: TokenRepository, mapper: JsonMapper) =
        GetUserUseCase(repository, mapper)
}