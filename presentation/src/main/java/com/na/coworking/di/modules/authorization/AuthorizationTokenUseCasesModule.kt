package com.na.coworking.di.modules.authorization

import com.na.coworking.data.di.TokenRepositoryScope
import com.na.coworking.data.di.authorization.TokenRepositoryModule
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import com.na.coworking.domain.usecases.authorization.GetTokenUseCase
import com.na.coworking.domain.usecases.authorization.HasLoginUseCase
import com.na.coworking.domain.usecases.authorization.LogoutUseCase
import com.na.coworking.domain.usecases.authorization.UpdateTokenUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [TokenRepositoryModule::class])
internal class AuthorizationTokenUseCasesModule {
    @Provides
    @TokenRepositoryScope
    fun provideFetTokenUseCase(repository: TokenRepository) = GetTokenUseCase(repository)

    @Provides
    @TokenRepositoryScope
    fun provideUpdateTokenUseCase(repository: TokenRepository) = UpdateTokenUseCase(repository)

    @Provides
    @TokenRepositoryScope
    fun provideDeleteTokenUseCase(repository: TokenRepository) = LogoutUseCase(repository)

    @Provides
    @TokenRepositoryScope
    fun provideHasLoginUseCase(repository: TokenRepository) = HasLoginUseCase(repository)
}