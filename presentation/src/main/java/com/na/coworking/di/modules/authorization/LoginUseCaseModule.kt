package com.na.coworking.di.modules.authorization

import android.content.Context
import com.na.coworking.appComponent
import com.na.coworking.data.di.AuthRepositoryScope
import com.na.coworking.domain.usecases.authorization.AuthorizeDataUseCase
import com.na.coworking.domain.usecases.authorization.LoginUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [AuthorizeDataUseCaseModule::class])
internal class LoginUseCaseModule {
    @Provides
    @AuthRepositoryScope
    fun provideLoginUseCase(
        context: Context,
        authorizeUseCase: AuthorizeDataUseCase
    ) = LoginUseCase(
        updateTokenUseCase = context.appComponent
            .getTokenUseCasesSubcomponent()
            .provideUpdateTokenUseCase(),
        authorizeUseCase = authorizeUseCase
    )
}