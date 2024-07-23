package com.na.coworking.data.di.authorization.binds

import com.na.coworking.data.authorization.AuthorizationRepoImpl
import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.domain.interfaces.authorization.AuthorizationRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class AuthorizationRepositoryBindModule {
    @Binds
    @AuthRepositoryScope
    abstract fun bindAuthorizationRepoImplToInterface(
        repository: AuthorizationRepoImpl
    ): AuthorizationRepository
}