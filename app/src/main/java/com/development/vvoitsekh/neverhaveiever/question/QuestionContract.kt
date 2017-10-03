package com.development.vvoitsekh.neverhaveiever.question

import com.development.vvoitsekh.neverhaveiever.data.Question


interface QuestionContract {
    interface View {
        fun showNextQuestion(question: Question)
    }

    interface Presenter {
        fun getQuestions(level: Int)

        fun getNextQuestion()

        fun showQuestion(id: Int)
    }
}