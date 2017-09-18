package com.development.vvoitsekh.neverhaveiever.question

import dagger.Binds
import dagger.Module


@Module
abstract class QuestionModule {

    @Binds
    abstract fun view(activity: QuestionActivity): QuestionContract.View

    @Binds
    abstract fun bindQuestionPresenter(presenter: QuestionPresenter): QuestionContract.Presenter
}