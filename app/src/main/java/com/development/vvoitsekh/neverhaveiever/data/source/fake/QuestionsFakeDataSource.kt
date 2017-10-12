package com.development.vvoitsekh.neverhaveiever.data.source.fake

import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsDataSource
import javax.inject.Inject

class QuestionsFakeDataSource @Inject constructor() : QuestionsDataSource {

    val kidsQuestions = arrayOf("kids 1", "kids 2", "kids 3", "kids 4", "kids 5")
    val normalQuestions = arrayOf("normal 1", "normal 2", "normal 3", "normal 4", "normal 5")
    val adultsQuestions = arrayOf("adults 1", "adults 2", "adults 3", "adults 4", "adults 5")

    override fun getQuestions(modes: BooleanArray): Array<Question> {
        var counter = 0
        var level = 2
        when(level) {
            1 -> return kidsQuestions.map { it -> Question(counter++, it, 1) }.toTypedArray()
            2 -> return normalQuestions.map { it -> Question(counter++, it, 2) }.toTypedArray()
            3 -> return adultsQuestions.map { it -> Question(counter++, it, 3) }.toTypedArray()
            else -> return emptyArray()
        }
    }
}