package com.na.coworking.data.di.authorization

import android.content.Context
import com.na.coworking.data.authorization.TokenDSImpl
import com.na.coworking.data.di.TokenScope
import com.na.coworking.data.di.authorization.binds.TokenDataSourceBindModule
import dagger.Module
import dagger.Provides

@Module(includes = [TokenDataSourceBindModule::class])
internal class TokenDataSourceModule {
    @Provides
    @TokenScope
    fun provideDataSource(context: Context): TokenDSImpl = TokenDSImpl(context)
}