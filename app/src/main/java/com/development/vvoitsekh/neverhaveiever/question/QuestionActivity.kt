package com.development.vvoitsekh.neverhaveiever.question

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextSwitcher
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.development.vvoitsekh.neverhaveiever.BaseActivity
import com.development.vvoitsekh.neverhaveiever.R
import com.development.vvoitsekh.neverhaveiever.data.Question
import dagger.android.AndroidInjection
import javax.inject.Inject


class QuestionActivity : BaseActivity(), QuestionContract.View {
    @Inject
    lateinit var mPresenter: QuestionPresenter

    @BindView(R.id.questionSwitcher)
    lateinit var mQuestionSwitcher: TextSwitcher

    @BindView(R.id.questionNextButton)
    lateinit var mNextQuestionButton: Button

    private lateinit var mCurrentQuestion: Question

    private lateinit var mGestureDetector: GestureDetector

    companion object {
        private val LEVEL = "level"
        private val QUESTION = "question"

        fun newIntent(context: Context, modes: BooleanArray): Intent {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra(LEVEL, modes)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        ButterKnife.bind(this)

        val typeface = Typeface.createFromAsset(assets, "fonts/Veles-Regular.0.9.2.otf")
        mAppName.typeface = typeface
        //val typeface = Typeface.createFromAsset(assets, "fonts/Pangolin-Regular.ttf")

        mQuestionSwitcher.setFactory({
            val textView = TextView(this)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.gravity = Gravity.CENTER
            layoutParams.marginEnd = 24
            layoutParams.marginStart = 24
            textView.layoutParams = layoutParams

            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val nightMode = pref.getBoolean("NeverIHaveEver.night_mode", false)
            textView.setTextColor(if (nightMode) Color.WHITE else Color.BLACK)
            textView.textSize = 20.0F
            // textView.typeface = typeface

            textView
        })
        mQuestionSwitcher.inAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_in)
        mQuestionSwitcher.outAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_out)

        mGestureDetector = GestureDetector(this, SwipeGestureDetector())


        mNextQuestionButton.setOnClickListener { mPresenter.getNextQuestion() }

        mPresenter.getQuestions(intent.extras.getBooleanArray(LEVEL))
        if (savedInstanceState != null) {
            mPresenter.showQuestion(savedInstanceState.getInt(QUESTION))
        } else {
            mPresenter.getNextQuestion()
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.mGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putInt(QUESTION, mCurrentQuestion.id)
        }
        super.onSaveInstanceState(outState)
    }

    override fun showNextQuestion(question: Question) {
        mQuestionSwitcher.setText(question.text)
        mCurrentQuestion = question
    }

    inner class SwipeGestureDetector : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            e1?.let {
                e2?.let {
                    return when (getSlope(e1.x, e1.y, e2.x, e2.y)) {
                        1 -> {
                            mPresenter.getNextQuestion()
                            true
                        }
                        else -> false
                    }
                }
            }
            return false
        }

        override fun onDown(e: MotionEvent?): Boolean = true

        private fun getSlope(x1: Float, y1: Float, x2: Float, y2: Float): Int {
            val angle = Math.toDegrees(Math.atan2((y1 - y2).toDouble(), (x2 - x1).toDouble()))
            if (angle > 45 && angle <= 135)
            // top
                return 1
            if (angle >= 135 && angle < 180 || angle < -135 && angle > -180)
            // left
                return 2
            if (angle < -45 && angle >= -135)
            // down
                return 3
            return if (angle > -45 && angle <= 45) 4 else 0
        }
    }
}
