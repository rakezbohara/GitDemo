package com.rakezb.gitdemo.di.component

import android.content.Context
import android.content.SharedPreferences
import com.rakezb.gitdemo.MyApplication
import com.rakezb.gitdemo.di.module.ApplicationModule

import com.rakezb.gitdemo.di.module.NetModule
import com.rakezb.gitdemo.ui.login.UserActivityViewModel

import dagger.Component
import javax.inject.Singleton


@Singleton

@Component(modules = arrayOf(ApplicationModule::class, NetModule::class))


interface ApplicationComponent {
    fun app(): MyApplication


    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(target: UserActivityViewModel)

}
