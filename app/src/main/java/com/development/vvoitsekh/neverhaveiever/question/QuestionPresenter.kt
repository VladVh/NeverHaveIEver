package com.development.vvoitsekh.neverhaveiever.question

import com.development.vvoitsekh.neverhaveiever.data.source.QuestionRepository
import javax.inject.Inject


class QuestionPresenter @Inject constructor(view: QuestionContract.View, repository: QuestionRepository) : QuestionContract.Presenter {

    private var mQuestionView : QuestionContract.View = view
    private var mQuestionRepository: QuestionRepository = repository


}