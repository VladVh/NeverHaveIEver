package com.development.vvoitsekh.neverhaveiever.question

import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsRepository
import javax.inject.Inject


class QuestionPresenter @Inject constructor(view: QuestionContract.View, repository: QuestionsRepository) : QuestionContract.Presenter {

    private var mQuestionView : QuestionContract.View = view
    private var mQuestionsRepository: QuestionsRepository = repository


}