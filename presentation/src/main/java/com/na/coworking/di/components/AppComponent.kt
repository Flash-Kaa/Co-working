package com.na.coworking.di.components

import com.na.coworking.MainActivity
import com.na.coworking.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}