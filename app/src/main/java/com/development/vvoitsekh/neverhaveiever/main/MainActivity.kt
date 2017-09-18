package com.development.vvoitsekh.neverhaveiever.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.development.vvoitsekh.neverhaveiever.R
import com.development.vvoitsekh.neverhaveiever.question.QuestionActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var mMainPresenter: MainPresenter

    @BindView(R.id.mainKidsButton)
    lateinit var mKidsButton: Button

    @BindView(R.id.mainNormalButton)
    lateinit var mNormalButton: Button

    @BindView(R.id.mainAdultButton)
    lateinit var mAdultButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this);
        mKidsButton.setOnClickListener { mMainPresenter.startKidsGame() }
        mNormalButton.setOnClickListener { mMainPresenter.startNormalGame() }
        mAdultButton.setOnClickListener { mMainPresenter.startAdultsGame() }
    }

    override fun showGame(level: Int) {
        startActivity(QuestionActivity.newIntent(this, level))
    }
}
