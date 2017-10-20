package com.development.vvoitsekh.neverhaveiever.data.source

import com.development.vvoitsekh.neverhaveiever.data.Question
import javax.inject.Inject
import javax.inject.Singleton

class QuestionsRepository @Inject constructor(localDataSource: QuestionsDataSource): QuestionsDataSource {

    private val mQuestionsDataSource: QuestionsDataSource = localDataSource

    private var mCachedQuestions: Array<Question> = emptyArray()
    private var mCachedModes: Array<Boolean> = emptyArray()

    override fun getQuestions(modes: BooleanArray): Array<Question> {
        if (mCachedQuestions.size == 0 || !mCachedModes.contentEquals(modes.toTypedArray()) ) {
            mCachedQuestions = mQuestionsDataSource.getQuestions(modes);
            mCachedModes = modes.copyOf().toTypedArray()
        }
        return mCachedQuestions
    }
}