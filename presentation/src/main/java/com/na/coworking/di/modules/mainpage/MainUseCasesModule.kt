package com.na.coworking.di.modules.mainpage

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.data.di.listofcoworking.ListOfCoworkingRepositoryModule
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository
import com.na.coworking.domain.usecases.listofcoworking.GetListOfCoworkingUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [ListOfCoworkingRepositoryModule::class])
internal class MainUseCasesModule {
    @Provides
    @ListOfCoworkingScope
    fun provideGetUseCase(
        repository: ListOfCoworkingRepository
    ): GetListOfCoworkingUseCase = GetListOfCoworkingUseCase(repository)
}