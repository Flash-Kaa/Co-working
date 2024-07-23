package com.na.coworking.data.di.authorization.binds

import com.na.coworking.data.authorization.TokenDSImpl
import com.na.coworking.data.di.TokenRepositoryScope
import com.na.coworking.domain.interfaces.authorization.TokenDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class TokenDataSourceBindModule {
    @Binds
    @TokenRepositoryScope
    abstract fun bindTokenDSImplToInterface(dataSource: TokenDSImpl): TokenDataSource
}