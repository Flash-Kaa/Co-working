package com.na.coworking.data.di.account

import com.na.coworking.data.account.UserRepositoryImpl
import com.na.coworking.data.di.UserScope
import com.na.coworking.data.di.account.binds.UserRepositoryBindModule
import com.na.coworking.domain.interfaces.account.UserDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [UserRepositoryBindModule::class, UserDataSourceModule::class])
class UserRepositoryModule {
    @Provides
    @UserScope
    fun provideRepository(dataSource: UserDataSource) = UserRepositoryImpl(dataSource)
}