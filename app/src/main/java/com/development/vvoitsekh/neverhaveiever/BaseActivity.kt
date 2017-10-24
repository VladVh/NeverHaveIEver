package com.development.vvoitsekh.neverhaveiever

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.development.vvoitsekh.neverhaveiever.util.LocaleHelper
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN




open class BaseActivity : AppCompatActivity() {

    @BindView(R.id.baseAppName)
    lateinit var mAppName: TextView

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val decorView = window.decorView
        // Hide the status bar.
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions


        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val nightMode = pref.getBoolean("NeverIHaveEver.night_mode", false)
        setTheme(if (nightMode) R.style.AppThemeDark else R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}