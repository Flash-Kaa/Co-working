package com.na.coworking

import android.app.Application
import com.na.coworking.di.components.AppComponent
import com.na.coworking.di.components.DaggerAppComponent
import com.na.coworking.di.modules.AppModule

class CoworkingApp: Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
    }

    private fun initDagger(app: CoworkingApp): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
    }
}