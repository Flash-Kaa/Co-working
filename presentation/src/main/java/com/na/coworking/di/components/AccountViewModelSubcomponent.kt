package com.na.coworking.di.components

import com.na.coworking.data.di.BookingsRepositoryScope
import com.na.coworking.di.modules.account.AccountViewModelFactoryModule
import com.na.coworking.ui.account.AccountVM
import dagger.Subcomponent

@Subcomponent(modules = [AccountViewModelFactoryModule::class])
@BookingsRepositoryScope
internal interface AccountViewModelSubcomponent {
    fun provideFactoryWrapper(): AccountVM.FactoryWrapperWithUseCases
}