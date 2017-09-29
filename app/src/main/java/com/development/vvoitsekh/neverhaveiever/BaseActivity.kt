package com.development.vvoitsekh.neverhaveiever

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.preference.PreferenceManager
import android.content.SharedPreferences



open class BaseActivity: AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val night_mode = pref.getBoolean("NeverIHaveEver.night_mode", false)
        setTheme(if (night_mode) R.style.AppThemeDark else R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}