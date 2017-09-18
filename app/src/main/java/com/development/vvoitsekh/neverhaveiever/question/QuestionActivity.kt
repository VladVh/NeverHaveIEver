package com.development.vvoitsekh.neverhaveiever.question

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

import com.development.vvoitsekh.neverhaveiever.R

class QuestionActivity : AppCompatActivity(), QuestionContract.View {

    @BindView(R.id.questionTextView)
    lateinit var mQuestionTextView: TextView

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
        mQuestionTextView.text = intent.extras.getInt(LEVEL).toString()
    }
}
