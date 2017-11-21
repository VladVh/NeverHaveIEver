package com.development.vvoitsekh.neverhaveiever.settings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.development.vvoitsekh.neverhaveiever.BaseActivity
import com.development.vvoitsekh.neverhaveiever.util.LocaleHelper


class SettingsActivity: Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}