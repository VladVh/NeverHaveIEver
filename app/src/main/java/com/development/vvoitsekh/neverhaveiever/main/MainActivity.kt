package com.development.vvoitsekh.neverhaveiever.main

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
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

//    @BindView(R.id.mainToolbar)
//    lateinit var mToolbar: Toolbar

    @BindView(R.id.mainPlayButton)
    lateinit var mPlayButton: ImageButton

    @BindView(R.id.mainLightButton)
    lateinit var mLightButton: ToggleButton

    @BindView(R.id.mainNormalButton)
    lateinit var mNormalButton: ToggleButton

    @BindView(R.id.mainExtremeButton)
    lateinit var mExtremeButton: ToggleButton

//    @BindView(R.id.mainSettingsButton)
//    lateinit var mSettingsButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        val typeface = Typeface.createFromAsset(assets, "fonts/Veles-Regular.0.9.2.otf")
        mAppName.typeface = typeface


        mPlayButton.setOnClickListener { mPresenter.startGame() }

        mLightButton.setOnClickListener { mPresenter.applyKidsMode() }
        mNormalButton.setOnClickListener { mPresenter.applyTeenagerMode() }
        mExtremeButton.setOnClickListener { mPresenter.applyAdultMode() }

        //mSettingsButton.setOnClickListener { startActivityForResult(Intent(this, SettingsActivity::class.java), 0) }
    }

    override fun showGame(modes: BooleanArray) {
        startActivity(QuestionActivity.newIntent(this, modes))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setDisplayHomeAsUpEnabled(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mainMenuSettingsButton -> {
                startActivityForResult(Intent(this, SettingsActivity::class.java), 0)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
