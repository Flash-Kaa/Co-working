package com.na.coworking.data.di.authorization

import android.content.Context
import com.na.coworking.data.authorization.TokenDSImpl
import com.na.coworking.data.di.TokenRepositoryScope
import com.na.coworking.data.di.authorization.binds.TokenDataSourceBindModule
import dagger.Module
import dagger.Provides

@Module(includes = [TokenDataSourceBindModule::class])
internal class TokenDataSourceModule {
    @Provides
    @TokenRepositoryScope
    fun provideDataSource(context: Context): TokenDSImpl = TokenDSImpl(context)
}