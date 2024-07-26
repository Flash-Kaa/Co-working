package com.na.coworking.data.di.authorization.binds

import com.na.coworking.data.authorization.TokenDSImpl
import com.na.coworking.data.di.TokenScope
import com.na.coworking.domain.interfaces.authorization.TokenDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class TokenDataSourceBindModule {
    @Binds
    @TokenScope
    abstract fun bindTokenDSImplToInterface(dataSource: TokenDSImpl): TokenDataSource
}