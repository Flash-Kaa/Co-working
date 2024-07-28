package com.na.coworking.data.di

import com.na.coworking.data.mapper.JsonMapperImpl
import com.na.coworking.domain.interfaces.JsonMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {
    @Provides
    fun provideMapper(): JsonMapper = JsonMapperImpl()
}