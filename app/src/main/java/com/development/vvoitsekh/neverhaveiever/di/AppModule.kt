package com.development.vvoitsekh.neverhaveiever.di

import android.content.Context
import com.development.vvoitsekh.neverhaveiever.App
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsDbHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApp(app: App) = app

    @Provides
    fun provideContext(app: App): Context = app

    @Provides
    @Singleton
    fun provideDbHelper(context: Context) = QuestionsDbHelper(context)

}