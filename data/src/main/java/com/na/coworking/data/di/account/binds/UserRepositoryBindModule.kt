package com.na.coworking.data.di.account.binds

import com.na.coworking.data.account.UserRepositoryImpl
import com.na.coworking.data.di.UserScope
import com.na.coworking.domain.interfaces.account.UserRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class UserRepositoryBindModule {
    @Binds
    @UserScope
    abstract fun bindUserRepositoryToInterface(repositoryImpl: UserRepositoryImpl): UserRepository
}