package com.na.coworking.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule(private val app: Application) {
    @Singleton
    @Provides
    fun provideContext(): Context = app
}