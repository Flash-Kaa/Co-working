package com.na.coworking.data.di.listofcoworking.binds

import com.na.coworking.data.di.ListOfCoworkingRepositoryScope
import com.na.coworking.data.listofcowowking.ListOfCoworkingRepoImpl
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class ListOfCoworkingRepositoryBindModule {
    @Binds
    @ListOfCoworkingRepositoryScope
    abstract fun bindRepositoryToInterface(
        repository: ListOfCoworkingRepoImpl
    ): ListOfCoworkingRepository
}