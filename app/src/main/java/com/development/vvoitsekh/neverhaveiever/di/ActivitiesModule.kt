package com.development.vvoitsekh.neverhaveiever.di

import com.development.vvoitsekh.neverhaveiever.main.MainActivity
import com.development.vvoitsekh.neverhaveiever.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by v.voitsekh on 13.09.2017.
 */
@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity
}