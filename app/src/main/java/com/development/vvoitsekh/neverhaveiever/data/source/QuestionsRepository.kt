package com.development.vvoitsekh.neverhaveiever.data.source

import com.development.vvoitsekh.neverhaveiever.data.Question
import javax.inject.Inject
import javax.inject.Singleton

class QuestionsRepository @Inject constructor(localDataSource: QuestionsDataSource): QuestionsDataSource {

    private val mQuestionsDataSource: QuestionsDataSource = localDataSource

    private var mCachedQuestions: Array<Question> = emptyArray()

    override fun getQuestions(level: Int): Array<Question> {
        if (mCachedQuestions.size == 0 || mCachedQuestions[0].level != level) {
            mCachedQuestions = mQuestionsDataSource.getQuestions(level);
        }
        return mCachedQuestions
    }
}