package com.development.vvoitsekh.neverhaveiever.question

import android.util.Log
import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.Callable
import javax.inject.Inject


class QuestionPresenter @Inject constructor(view: QuestionContract.View, repository: QuestionsRepository) : QuestionContract.Presenter {
    private var mQuestionView : QuestionContract.View = view
    private var mQuestionsRepository: QuestionsRepository = repository

    private var mQuestions: Array<Question> = emptyArray()

    override fun getQuestions(modes: BooleanArray) {
        val observable = Observable.fromCallable(object : Callable<Array<Question>> {
            override fun call(): Array<Question> {
                return mQuestionsRepository.getQuestions(modes)
            }
        })
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            mQuestions = result.copyOf()
                            getNextQuestion() },
                        { error -> Log.e("DB error", "Error obtaining data from the database")}
                )
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