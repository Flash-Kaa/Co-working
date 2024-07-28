package com.na.coworking.di.components

import com.na.coworking.data.di.MapperModule
import com.na.coworking.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MapperModule::class])
internal interface AppComponent {
    fun provideTokenUseCasesComponent(): AuthorizationTokenUseCasesSubcomponent
}