package com.na.coworking.data.di.listofcoworking.binds

import com.na.coworking.data.di.ListOfCoworkingScope
import com.na.coworking.data.listofcowowking.ListOfCoworkingRepoImpl
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class ListOfCoworkingRepositoryBindModule {
    @Binds
    @ListOfCoworkingScope
    abstract fun bindRepositoryToInterface(
        repository: ListOfCoworkingRepoImpl
    ): ListOfCoworkingRepository
}