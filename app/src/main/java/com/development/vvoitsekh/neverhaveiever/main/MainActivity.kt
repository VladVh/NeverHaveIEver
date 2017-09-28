package com.development.vvoitsekh.neverhaveiever.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.development.vvoitsekh.neverhaveiever.BaseActivity
import com.development.vvoitsekh.neverhaveiever.R
import com.development.vvoitsekh.neverhaveiever.question.QuestionActivity
import com.development.vvoitsekh.neverhaveiever.settings.SettingsActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var mPresenter: MainPresenter

    @BindView(R.id.mainKidsButton)
    lateinit var mKidsButton: Button

    @BindView(R.id.mainNormalButton)
    lateinit var mNormalButton: Button

    @BindView(R.id.mainAdultButton)
    lateinit var mAdultButton: Button

    @BindView(R.id.mainSettingsButton)
    lateinit var mSettingsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this);
        mKidsButton.setOnClickListener { mPresenter.startKidsGame() }
        mNormalButton.setOnClickListener { mPresenter.startNormalGame() }
        mAdultButton.setOnClickListener { mPresenter.startAdultsGame() }
        mSettingsButton.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }
    }

    override fun showGame(level: Int) {
        startActivity(QuestionActivity.newIntent(this, level))
    }
}
