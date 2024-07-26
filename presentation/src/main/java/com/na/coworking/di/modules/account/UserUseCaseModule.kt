package com.na.coworking.di.modules.account

import com.na.coworking.data.di.UserScope
import com.na.coworking.data.di.account.UserRepositoryModule
import com.na.coworking.domain.interfaces.account.UserRepository
import com.na.coworking.domain.usecases.account.GetUserUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [UserRepositoryModule::class])
internal class UserUseCaseModule {
    @Provides
    @UserScope
    fun provideGetUserUseCase(repository: UserRepository) = GetUserUseCase(repository)
}