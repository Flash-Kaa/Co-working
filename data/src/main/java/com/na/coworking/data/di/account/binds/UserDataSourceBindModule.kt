package com.na.coworking.data.di.account.binds

import com.na.coworking.data.account.UserDataSourceImpl
import com.na.coworking.data.di.UserScope
import com.na.coworking.domain.interfaces.account.UserDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class UserDataSourceBindModule {
    @Binds
    @UserScope
    abstract fun bindUserDataSourceToInterface(dataSource: UserDataSourceImpl): UserDataSource
}