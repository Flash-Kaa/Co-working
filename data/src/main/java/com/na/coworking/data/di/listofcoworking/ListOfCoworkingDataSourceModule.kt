package com.na.coworking.data.di.listofcoworking

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.data.di.listofcoworking.binds.ListOfCoworkingDataSourceBindModule
import com.na.coworking.data.listofcowowking.ListOfCoworkingDSImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ListOfCoworkingDataSourceBindModule::class])
internal class ListOfCoworkingDataSourceModule {
    @Provides
    @ListOfCoworkingScope
    fun provideDataSource(): ListOfCoworkingDSImpl = ListOfCoworkingDSImpl()
}