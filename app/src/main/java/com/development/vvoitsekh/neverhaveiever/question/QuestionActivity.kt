package com.development.vvoitsekh.neverhaveiever.question

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MotionEventCompat
import android.transition.Transition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife

import com.development.vvoitsekh.neverhaveiever.R
import com.development.vvoitsekh.neverhaveiever.data.Question
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.development.vvoitsekh.neverhaveiever.BaseActivity


class QuestionActivity : BaseActivity(), QuestionContract.View {
    @Inject
    lateinit var mPresenter: QuestionPresenter

    @BindView(R.id.questionSwitcher)
    lateinit var mQuestionSwitcher: TextSwitcher

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
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        ButterKnife.bind(this)

        mQuestionSwitcher.setFactory({
            val textView = TextView(this)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.gravity = Gravity.CENTER
            textView.layoutParams = layoutParams
            textView.setTextColor(Color.BLACK)
            textView.textSize = 20.0F
            textView
        })
        mQuestionSwitcher.inAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_in)
        mQuestionSwitcher.outAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_out)

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
        mQuestionSwitcher.setText(question.text)

    }
}
