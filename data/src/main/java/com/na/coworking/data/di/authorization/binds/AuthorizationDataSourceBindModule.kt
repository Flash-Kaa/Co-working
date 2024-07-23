package com.na.coworking.data.di.authorization.binds

import com.na.coworking.data.authorization.AuthorizationDSImpl
import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.domain.interfaces.authorization.AuthorizationDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class AuthorizationDataSourceBindModule {
    @Binds
    @AuthRepositoryScope
    abstract fun bindAuthorizationDSImplToInterface(
        dataSource: AuthorizationDSImpl
    ): AuthorizationDataSource
}