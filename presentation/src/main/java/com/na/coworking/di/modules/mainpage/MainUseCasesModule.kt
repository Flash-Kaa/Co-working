package com.na.coworking.di.modules.mainpage

import com.na.coworking.data.di.ListOfCoworkingRepositoryScope
import com.na.coworking.data.di.listofcoworking.ListOfCoworkingRepositoryModule
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository
import com.na.coworking.domain.usecases.listofcoworking.GetListUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [ListOfCoworkingRepositoryModule::class])
internal class MainUseCasesModule {
    @Provides
    @ListOfCoworkingRepositoryScope
    fun provideGetUseCase(
        repository: ListOfCoworkingRepository
    ): GetListUseCase = GetListUseCase(repository)
}