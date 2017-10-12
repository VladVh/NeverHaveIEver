package com.development.vvoitsekh.neverhaveiever.question

import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsRepository
import java.util.*
import javax.inject.Inject


class QuestionPresenter @Inject constructor(view: QuestionContract.View, repository: QuestionsRepository) : QuestionContract.Presenter {
    private var mQuestionView : QuestionContract.View = view
    private var mQuestionsRepository: QuestionsRepository = repository

    private var mQuestions: Array<Question> = emptyArray()

    override fun getQuestions(modes: BooleanArray) {
        mQuestions = mQuestionsRepository.getQuestions(modes)
    }

    override fun getNextQuestion() {
        mQuestionView.showNextQuestion(getRandomQuestion())
    }

    override fun showQuestion(id: Int) {
        mQuestionView.showNextQuestion(mQuestions.single { it -> it.id == id })
    }

    private fun getRandomQuestion(): Question {
        var rand = Random()
        var index = rand.nextInt(mQuestions.size)
        return mQuestions.get(index)
    }
}