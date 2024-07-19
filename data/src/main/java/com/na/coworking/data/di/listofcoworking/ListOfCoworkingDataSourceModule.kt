package com.na.coworking.data.di.listofcoworking

import com.na.coworking.data.di.ListOfCoworkingRepositoryScope
import com.na.coworking.data.di.listofcoworking.binds.ListOfCoworkingDataSourceBindModule
import com.na.coworking.data.listofcowowking.ListOfCoworkingDSImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ListOfCoworkingDataSourceBindModule::class])
internal class ListOfCoworkingDataSourceModule {
    @Provides
    @ListOfCoworkingRepositoryScope
    fun provideDataSource(): ListOfCoworkingDSImpl = ListOfCoworkingDSImpl()
}