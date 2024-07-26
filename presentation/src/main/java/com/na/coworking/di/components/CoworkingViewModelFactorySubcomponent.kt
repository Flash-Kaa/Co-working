package com.na.coworking.di.components

import com.na.coworking.di.modules.coworking.CoworkingViewModelFactoryModule
import com.na.coworking.ui.coworking.CoworkingVM
import dagger.Subcomponent

@Subcomponent(modules = [CoworkingViewModelFactoryModule::class])
internal interface CoworkingViewModelFactorySubcomponent {
    fun provideFactoryWrapper(): CoworkingVM.FactoryWrapperWithUseCases
}