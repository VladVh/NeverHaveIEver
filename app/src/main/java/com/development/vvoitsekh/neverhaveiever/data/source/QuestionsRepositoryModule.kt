package com.development.vvoitsekh.neverhaveiever.data.source

import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class QuestionsRepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionsRepository(localDataSource: QuestionsLocalDataSource) = QuestionsRepository(localDataSource)
}