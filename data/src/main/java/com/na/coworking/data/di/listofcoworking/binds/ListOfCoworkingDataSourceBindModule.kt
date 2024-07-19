package com.na.coworking.data.di.listofcoworking.binds

import com.na.coworking.data.di.ListOfCoworkingRepositoryScope
import com.na.coworking.data.listofcowowking.ListOfCoworkingDSImpl
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingDataSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class ListOfCoworkingDataSourceBindModule {
    @Binds
    @ListOfCoworkingRepositoryScope
    abstract fun bindDataSourceImplToInterface(
        dataSource: ListOfCoworkingDSImpl
    ): ListOfCoworkingDataSource
}