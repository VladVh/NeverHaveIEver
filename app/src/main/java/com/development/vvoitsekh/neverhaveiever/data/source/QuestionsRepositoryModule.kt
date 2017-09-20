package com.development.vvoitsekh.neverhaveiever.data.source

import com.development.vvoitsekh.neverhaveiever.data.source.fake.QuestionsFakeDataSource
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsDbHelper
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class QuestionsRepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionsLocalDataSource() = QuestionsLocalDataSource()

    @Provides
    @Singleton
    fun provideQuestionsRepository(localDataSource: QuestionsLocalDataSource) = QuestionsRepository(localDataSource)
}