package com.na.coworking.di.components

import com.na.coworking.data.di.ListOfCoworkingRepositoryScope
import com.na.coworking.di.modules.mainpage.MainViewModelFactoryModule
import com.na.coworking.ui.mainpage.MainPageVM
import dagger.Subcomponent

@ListOfCoworkingRepositoryScope
@Subcomponent(modules = [MainViewModelFactoryModule::class])
internal interface MainViewModelSubcomponent {
    fun provideFactoryWrapper(): MainPageVM.FactoryWrapperWithUseCases
}