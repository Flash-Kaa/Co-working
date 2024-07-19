package com.na.coworking

import android.content.Context
import com.na.coworking.di.components.AppComponent

internal val Context.appComponent: AppComponent
    get() = when (this) {
        is CoworkingApp -> this.appComponent
        else -> this.applicationContext.appComponent
    }