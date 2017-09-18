package com.development.vvoitsekh.neverhaveiever.di

import com.development.vvoitsekh.neverhaveiever.main.MainActivity
import com.development.vvoitsekh.neverhaveiever.main.MainModule
import com.development.vvoitsekh.neverhaveiever.question.QuestionActivity
import com.development.vvoitsekh.neverhaveiever.question.QuestionModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(QuestionModule::class))
    abstract fun bindQuestionActivity(): QuestionActivity
}