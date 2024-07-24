package com.na.coworking.di.modules.authorization

import android.content.Context
import com.na.coworking.appComponent
import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.domain.usecases.authorization.LoginUseCase
import com.na.coworking.ui.authorization.AuthorizationVM
import dagger.Module
import dagger.Provides

@Module(includes = [LoginUseCaseModule::class])
internal class AuthorizationViewModelFactoryModule {
    @Provides
    @AuthRepositoryScope
    fun provideViewModel(context: Context, loginUseCase: LoginUseCase) =
        AuthorizationVM.FactoryWrapperWithUseCases(
            loginUseCase = loginUseCase,
            hasLoginUseCase = context.appComponent
                .getTokenUseCasesSubcomponent()
                .provideHasLoginUseCase()
        )
}