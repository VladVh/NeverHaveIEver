package com.development.vvoitsekh.neverhaveiever.di

import com.development.vvoitsekh.neverhaveiever.App
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionRepository
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        AppModule::class))
interface AppComponent {
    fun inject(app: App)

    //fun getRepository(): QuestionRepository
}