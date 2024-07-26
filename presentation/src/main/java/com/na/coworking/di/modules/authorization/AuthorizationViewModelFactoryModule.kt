package com.na.coworking.di.modules.authorization

import com.na.coworking.data.di.AuthorizationScope
import com.na.coworking.domain.usecases.authorization.HasLoginUseCase
import com.na.coworking.domain.usecases.authorization.LoginUseCase
import com.na.coworking.ui.authorization.AuthorizationVM
import dagger.Module
import dagger.Provides

@Module(includes = [LoginUseCaseModule::class])
internal class AuthorizationViewModelFactoryModule {
    @Provides
    @AuthorizationScope
    fun provideViewModel(
        loginUseCase: LoginUseCase,
        hasLoginUseCase: HasLoginUseCase
    ) = AuthorizationVM.FactoryWrapperWithUseCases(
        loginUseCase = loginUseCase,
        hasLoginUseCase = hasLoginUseCase
    )
}