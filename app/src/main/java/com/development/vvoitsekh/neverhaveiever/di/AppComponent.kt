package com.development.vvoitsekh.neverhaveiever.di

import com.development.vvoitsekh.neverhaveiever.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by v.voitsekh on 13.09.2017.
 */

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        AppModule::class))
interface AppComponent {
    fun inject(app: App)
}