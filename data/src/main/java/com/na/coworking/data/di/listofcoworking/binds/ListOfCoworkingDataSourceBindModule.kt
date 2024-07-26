package com.na.coworking.data.di.listofcoworking.binds

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.data.listofcowowking.ListOfCoworkingDSImpl
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class ListOfCoworkingDataSourceBindModule {
    @Binds
    @ListOfCoworkingScope
    abstract fun bindDataSourceImplToInterface(
        dataSource: ListOfCoworkingDSImpl
    ): ListOfCoworkingDataSource
}