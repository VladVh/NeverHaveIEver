package com.development.vvoitsekh.neverhaveiever.di

import android.content.Context
import com.development.vvoitsekh.neverhaveiever.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApp(app: App): Context = app


}