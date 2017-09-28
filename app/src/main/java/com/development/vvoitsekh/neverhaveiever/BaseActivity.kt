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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val themeName = pref.getString("prefSyncFrequency3", "AppTheme")
        if (themeName.equals("AppTheme")) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(R.style.AppThemeDark)
        }
        super.onCreate(savedInstanceState, persistentState)
    }
}