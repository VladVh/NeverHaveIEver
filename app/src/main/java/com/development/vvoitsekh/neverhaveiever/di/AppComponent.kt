package com.development.vvoitsekh.neverhaveiever.di

import com.development.vvoitsekh.neverhaveiever.App
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsRepositoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance




@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        AppModule::class,
        QuestionsRepositoryModule::class))
interface AppComponent {
    fun inject(app: App)

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: App): Builder
//        fun build(): AppComponent
//    }
    //fun getRepository(): QuestionsRepository
}