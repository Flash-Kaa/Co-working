package com.na.coworking.data.di.authorization.binds

import com.na.coworking.data.authorization.TokenRepoImpl
import com.na.coworking.data.di.TokenScope
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class TokenRepositoryBindModule {
    @Binds
    @TokenScope
    abstract fun bindTokenRepositoryImplToInterface(repository: TokenRepoImpl): TokenRepository
}