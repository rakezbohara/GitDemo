package com.rakezb.gitdemo

import androidx.multidex.MultiDexApplication
import com.rakezb.gitdemo.di.component.ApplicationComponent
import com.rakezb.gitdemo.di.component.DaggerApplicationComponent
import com.rakezb.gitdemo.di.module.ApplicationModule


class MyApplication : MultiDexApplication() {

    companion object{
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}

