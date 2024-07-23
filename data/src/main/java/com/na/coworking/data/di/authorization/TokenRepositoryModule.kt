package com.na.coworking.data.di.authorization

import com.na.coworking.data.authorization.TokenRepoImpl
import com.na.coworking.data.di.TokenRepositoryScope
import com.na.coworking.data.di.authorization.binds.TokenRepositoryBindModule
import com.na.coworking.domain.interfaces.authorization.TokenDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [TokenDataSourceModule::class, TokenRepositoryBindModule::class])
class TokenRepositoryModule {
    @Provides
    @TokenRepositoryScope
    fun provideRepository(dataSource: TokenDataSource) = TokenRepoImpl(dataSource)
}