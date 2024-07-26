package com.na.coworking.data.di.account

import com.na.coworking.data.account.UserDataSourceImpl
import com.na.coworking.data.di.UserScope
import com.na.coworking.data.di.account.binds.UserDataSourceBindModule
import dagger.Module
import dagger.Provides

@Module(includes = [UserDataSourceBindModule::class])
internal class UserDataSourceModule {
    @Provides
    @UserScope
    fun provideDataSource() = UserDataSourceImpl()
}