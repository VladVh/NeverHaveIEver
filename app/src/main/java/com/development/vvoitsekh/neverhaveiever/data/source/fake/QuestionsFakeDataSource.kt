package com.development.vvoitsekh.neverhaveiever.data.source.fake

import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsDataSource
import javax.inject.Inject

class QuestionsFakeDataSource @Inject constructor() : QuestionsDataSource {

    val kidsQuestions = arrayOf("kids 1", "kids 2", "kids 3", "kids 4", "kids 5")
    val normalQuestions = arrayOf("normal 1", "normal 2", "normal 3", "normal 4", "normal 5")
    val adultsQuestions = arrayOf("adults 1", "adults 2", "adults 3", "adults 4", "adults 5")

    override fun getQuestions(level: Int): Array<Question> {
        when(level) {
            1 -> return kidsQuestions.map { it -> Question(it, 1) }.toTypedArray()
            2 -> return normalQuestions.map { it -> Question(it, 2) }.toTypedArray()
            3 -> return adultsQuestions.map { it -> Question(it, 3) }.toTypedArray()
            else -> return emptyArray()
        }
    }
}