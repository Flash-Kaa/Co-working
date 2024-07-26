package com.na.coworking.di.components

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.di.modules.mainpage.MainViewModelFactoryModule
import com.na.coworking.ui.mainpage.MainPageVM
import dagger.Subcomponent

@ListOfCoworkingScope
@Subcomponent(modules = [MainViewModelFactoryModule::class])
internal interface MainViewModelSubcomponent {
    fun provideFactoryWrapper(): MainPageVM.FactoryWrapperWithUseCases
}