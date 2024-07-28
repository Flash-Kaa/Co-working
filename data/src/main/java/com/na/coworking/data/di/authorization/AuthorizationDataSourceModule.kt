package com.na.coworking.data.di.authorization

import com.na.coworking.data.authorization.AuthorizationDSImpl
import com.na.coworking.data.di.AuthorizationScope
import com.na.coworking.data.di.authorization.binds.AuthorizationDataSourceBindModule
import com.na.coworking.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizationDataSourceBindModule::class])
internal class AuthorizationDataSourceModule {
    @Provides
    @AuthorizationScope
    fun provideDataSource(service: ApiService) = AuthorizationDSImpl(service)
}