package com.rakezb.gitdemo

import androidx.multidex.MultiDexApplication
import com.rakezb.gitdemo.di.component.DaggerApplicationComponent
import com.rakezb.gitdemo.di.module.ApplicationModule


abstract class MyApplication : MultiDexApplication() {

    val component by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}

