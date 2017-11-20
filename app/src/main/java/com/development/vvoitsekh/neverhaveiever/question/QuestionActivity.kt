package com.development.vvoitsekh.neverhaveiever.question

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.development.vvoitsekh.neverhaveiever.BaseActivity
import com.development.vvoitsekh.neverhaveiever.R
import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.settings.SettingsActivity
import com.development.vvoitsekh.neverhaveiever.util.ConnectivityStatus
import com.development.vvoitsekh.neverhaveiever.util.PrefUtil
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
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
    private lateinit var mModes: BooleanArray
    private var mQuestionId = -1
    private var mAdCounter = 0
    private val AdThreshold = 30

    private lateinit var mGestureDetector: GestureDetector

    private lateinit var mInterstitialAd: InterstitialAd

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

        //MobileAds.initialize(this, "ca-app-pub-6434220602939969~9178893473")
        if (!mAdBanner.isLoading)
            mAdBanner.loadAd(AdRequest.Builder().build())
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-6434220602939969/1324454182"
        mInterstitialAd.adListener = object: AdListener() {

            override fun onAdLoaded() {
                mInterstitialAd.show()
                mAdCounter = 0
            }
        }

        val typeface = Typeface.createFromAsset(assets, "fonts/Veles-Regular.0.9.2.otf")
        mAppName.typeface = typeface

        mQuestionSwitcher.setFactory({
            val textView = TextView(this)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.gravity = Gravity.CENTER
            layoutParams.marginEnd = 40
            layoutParams.marginStart = 40
            textView.layoutParams = layoutParams

            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val nightMode = pref.getBoolean("NeverIHaveEver.night_mode", false)
            textView.setTextColor(if (nightMode) Color.WHITE else Color.BLACK)
            textView.textSize = 22.0F
            textView.setLineSpacing(3F, 1.1F)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            // textView.typeface = typeface

            textView
        })
        mQuestionSwitcher.inAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_in)
        mQuestionSwitcher.outAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_out)

        mGestureDetector = GestureDetector(this, SwipeGestureDetector())

        mNextQuestionButton.setOnClickListener {
            mPresenter.getNextQuestion()
            mAdCounter++
            if (ConnectivityStatus.isConnected(this) && mAdCounter >= AdThreshold) {
                if (!mInterstitialAd.isLoading || !mInterstitialAd.isLoaded)
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }

        if (savedInstanceState != null) {
            mPresenter.getQuestions(savedInstanceState.getBooleanArray(LEVEL))
            mModes = savedInstanceState.getBooleanArray(LEVEL)
        } else {
            mPresenter.getQuestions(intent.extras.getBooleanArray(LEVEL))
            mModes = intent.extras.getBooleanArray(LEVEL).copyOf()
            mPresenter.getNextQuestion()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        startActivity(newIntent(this, mModes))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(if (PrefUtil.isNightModeOn(this)) R.menu.menu_main_dark else R.menu.menu_main_light, menu)
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.mGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putInt(QUESTION, mCurrentQuestion.id)
            outState.putBooleanArray(LEVEL, mModes)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        if (mQuestionId != -1) {
            mPresenter.showQuestion(mQuestionId)
            mQuestionId = -1
        }
        super.onResume()
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
