package com.na.coworking.di.components

import com.na.coworking.data.di.AuthorizationScope
import com.na.coworking.di.modules.authorization.AuthorizationViewModelFactoryModule
import com.na.coworking.ui.authorization.AuthorizationVM
import dagger.Subcomponent

@AuthorizationScope
@Subcomponent(modules = [AuthorizationViewModelFactoryModule::class])
internal interface AuthorizationViewModelSubcomponent {
    fun provideFactoryWrapper(): AuthorizationVM.FactoryWrapperWithUseCases
}