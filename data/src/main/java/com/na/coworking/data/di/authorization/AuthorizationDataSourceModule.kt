package com.na.coworking.data.di.authorization

import com.na.coworking.data.authorization.AuthorizationDSImpl
import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.data.di.authorization.binds.AuthorizationDataSourceBindModule
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizationDataSourceBindModule::class])
internal class AuthorizationDataSourceModule {
    @Provides
    @AuthRepositoryScope
    fun provideDataSource() = AuthorizationDSImpl()
}