package com.development.vvoitsekh.neverhaveiever.data.source

import com.development.vvoitsekh.neverhaveiever.data.Question

interface QuestionsDataSource {
    fun getQuestions(modes: BooleanArray): Array<Question>
}