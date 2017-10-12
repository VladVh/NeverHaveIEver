package com.development.vvoitsekh.neverhaveiever.data.source

import android.content.Context
import com.development.vvoitsekh.neverhaveiever.data.source.fake.QuestionsFakeDataSource
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsDbHelper
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class QuestionsRepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionsRepository(localDataSource: QuestionsFakeDataSource) = QuestionsRepository(localDataSource)
}