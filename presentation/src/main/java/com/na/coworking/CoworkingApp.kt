package com.na.coworking

import android.app.Application
import com.na.coworking.di.components.AppComponent
import com.na.coworking.di.components.DaggerAppComponent
import com.na.coworking.di.modules.AppModule
import javax.inject.Inject

internal class CoworkingApp : Application() {
    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: CoworkingApp): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
    }
}