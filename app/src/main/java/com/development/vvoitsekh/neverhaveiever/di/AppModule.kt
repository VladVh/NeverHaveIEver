package com.development.vvoitsekh.neverhaveiever.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application) = app.applicationContext

}