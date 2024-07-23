package com.na.coworking.data.di.authorization.binds

import com.na.coworking.data.authorization.TokenRepoImpl
import com.na.coworking.data.di.TokenRepositoryScope
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class TokenRepositoryBindModule {
    @Binds
    @TokenRepositoryScope
    abstract fun bindTokenRepositoryImplToInterface(repository: TokenRepoImpl): TokenRepository
}