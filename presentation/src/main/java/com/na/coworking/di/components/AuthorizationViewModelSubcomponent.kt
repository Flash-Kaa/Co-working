package com.na.coworking.di.components

import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.di.modules.authorization.AuthorizationViewModelFactoryModule
import com.na.coworking.ui.authorization.AuthorizationVM
import dagger.Subcomponent

@AuthRepositoryScope
@Subcomponent(modules = [AuthorizationViewModelFactoryModule::class])
internal interface AuthorizationViewModelSubcomponent {
    fun provideFactoryWrapper(): AuthorizationVM.FactoryWrapperWithUseCases
}