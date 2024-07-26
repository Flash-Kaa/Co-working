package com.na.coworking.di.components

import com.na.coworking.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
internal interface AppComponent {
    fun getMainPageVMSubcomponent(): MainViewModelSubcomponent

    fun provideTokenUseCasesComponent(): AuthorizationTokenUseCasesSubcomponent
}