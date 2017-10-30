package com.development.vvoitsekh.neverhaveiever.main

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ToggleButton
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

    @BindView(R.id.mainPlayButton)
    lateinit var mPlayButton: Button

    @BindView(R.id.mainKidsButton)
    lateinit var mKidsButton: ToggleButton

    @BindView(R.id.mainTeenagerButton)
    lateinit var mTeenagerButton: ToggleButton

    @BindView(R.id.mainAdultButton)
    lateinit var mAdultButton: ToggleButton

    @BindView(R.id.mainSettingsButton)
    lateinit var mSettingsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        val typeface = Typeface.createFromAsset(assets, "fonts/Veles-Regular.0.9.2.otf")
        mAppName.typeface = typeface

        mPlayButton.setOnClickListener { mPresenter.startGame() }

        mKidsButton.setOnClickListener { mPresenter.applyKidsMode() }
        mTeenagerButton.setOnClickListener { mPresenter.applyTeenagerMode() }
        mAdultButton.setOnClickListener { mPresenter.applyAdultMode() }

        mSettingsButton.setOnClickListener { startActivityForResult(Intent(this, SettingsActivity::class.java), 0) }
    }

    override fun showGame(modes: BooleanArray) {
        startActivity(QuestionActivity.newIntent(this, modes))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
