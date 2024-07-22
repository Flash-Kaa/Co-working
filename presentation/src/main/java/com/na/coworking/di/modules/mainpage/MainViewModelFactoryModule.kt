package com.na.coworking.di.modules.mainpage

import com.na.coworking.data.di.ListOfCoworkingRepositoryScope
import com.na.coworking.domain.usecases.listofcoworking.GetListOfCoworkingUseCase
import com.na.coworking.ui.mainpage.MainPageVM
import dagger.Module
import dagger.Provides

@Module(includes = [MainUseCasesModule::class])
internal class MainViewModelFactoryModule {
    @Provides
    @ListOfCoworkingRepositoryScope
    fun provideViewModel(
        getListOfCoworkingUseCase: GetListOfCoworkingUseCase
    ): MainPageVM.FactoryWrapperWithUseCases = MainPageVM.FactoryWrapperWithUseCases(
        getListOfCoworkingUseCase = getListOfCoworkingUseCase
    )
}