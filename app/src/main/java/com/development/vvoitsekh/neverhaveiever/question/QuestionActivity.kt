package com.development.vvoitsekh.neverhaveiever.question

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MotionEventCompat
import android.view.MotionEvent
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

import com.development.vvoitsekh.neverhaveiever.R
import com.development.vvoitsekh.neverhaveiever.data.Question
import javax.inject.Inject

class QuestionActivity : AppCompatActivity(), QuestionContract.View {
    @Inject
    lateinit var mPresenter: QuestionPresenter

    @BindView(R.id.questionTextView)
    lateinit var mQuestionTextView: TextView

    @BindView(R.id.questionRelativeLayout)
    lateinit var mQuestionLayout: RelativeLayout

    companion object {
        private val LEVEL = "level"

        fun newIntent(context: Context, level: Int): Intent {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra(LEVEL, level)
            return intent;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        ButterKnife.bind(this)
        mPresenter.getQuestions(intent.extras.getInt(LEVEL))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = MotionEventCompat.getActionMasked(event);

        when (action) {
            MotionEvent.ACTION_UP -> mPresenter.getNextQuestion()
        }
        return super.onTouchEvent(event)
    }

    override fun showNextQuestion(question: Question) {
        mQuestionTextView.text = question.text
    }
}
