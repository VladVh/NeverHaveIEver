package com.development.vvoitsekh.neverhaveiever.question

import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsRepository
import java.util.*
import javax.inject.Inject


class QuestionPresenter @Inject constructor(view: QuestionContract.View, repository: QuestionsRepository) : QuestionContract.Presenter {
    private var mQuestionView : QuestionContract.View = view
    private var mQuestionsRepository: QuestionsRepository = repository

    private var mQuestions: Array<Question> = emptyArray()

    override fun getQuestions(level: Int) {
        mQuestions = mQuestionsRepository.getQuestions(level)
        mQuestionView.showNextQuestion(getRandomQuestion())
    }

    override fun getNextQuestion() {
        mQuestionView.showNextQuestion(getRandomQuestion())
    }

    private fun getRandomQuestion(): Question {
        var rand = Random()
        var index = rand.nextInt(mQuestions.size)
        return mQuestions.get(index)
    }

}