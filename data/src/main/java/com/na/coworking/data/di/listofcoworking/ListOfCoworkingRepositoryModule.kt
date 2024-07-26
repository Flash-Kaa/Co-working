package com.na.coworking.data.di.listofcoworking

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.data.di.listofcoworking.binds.ListOfCoworkingRepositoryBindModule
import com.na.coworking.data.listofcowowking.ListOfCoworkingRepoImpl
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [ListOfCoworkingDataSourceModule::class, ListOfCoworkingRepositoryBindModule::class])
class ListOfCoworkingRepositoryModule {
    @Provides
    @ListOfCoworkingScope
    fun provideRepository(
        dataSource: ListOfCoworkingDataSource
    ): ListOfCoworkingRepoImpl = ListOfCoworkingRepoImpl(dataSource)
}