package com.rakezb.gitdemo.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rakezb.gitdemo.R
import com.rakezb.gitdemo.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


@Module
class ApplicationModule(var remitApplication: MyApplication) {


    @Provides
    @Singleton
    fun provideApp(): MyApplication = remitApplication

    @Provides
    @Singleton
    fun provideContext(): Context = remitApplication.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(remitApplication)

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
    }
}
