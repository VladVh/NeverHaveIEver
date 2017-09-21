package com.development.vvoitsekh.neverhaveiever.question

import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsRepositoryModule
import dagger.Binds
import dagger.Module


@Module
abstract class QuestionModule {

    @Binds
    abstract fun view(activity: QuestionActivity): QuestionContract.View

    @Binds
    abstract fun bindQuestionPresenter(presenter: QuestionPresenter): QuestionContract.Presenter
}