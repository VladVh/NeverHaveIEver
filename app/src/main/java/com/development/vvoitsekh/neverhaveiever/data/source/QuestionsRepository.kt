package com.development.vvoitsekh.neverhaveiever.data.source

import com.development.vvoitsekh.neverhaveiever.data.Question
import javax.inject.Inject
import javax.inject.Singleton

class QuestionsRepository @Inject constructor(localDataSource: QuestionsDataSource): QuestionsDataSource {

    private val mQuestionsDataSource: QuestionsDataSource = localDataSource

    override fun getQuestions(modes: BooleanArray): Array<Question> {
        return mQuestionsDataSource.getQuestions(modes)
    }
}