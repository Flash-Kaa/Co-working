package com.na.coworking.data.di.authorization

import com.na.coworking.data.authorization.AuthorizationRepoImpl
import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.data.di.authorization.binds.AuthorizationRepositoryBindModule
import com.na.coworking.domain.interfaces.authorization.AuthorizationDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizationDataSourceModule::class, AuthorizationRepositoryBindModule::class])
class AuthorizationRepositoryModule {
    @Provides
    @AuthRepositoryScope
    fun provideRepository(dataSource: AuthorizationDataSource) = AuthorizationRepoImpl(dataSource)
}