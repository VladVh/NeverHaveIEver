package com.development.vvoitsekh.neverhaveiever.settings

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.development.vvoitsekh.neverhaveiever.BaseActivity


class SettingsActivity: BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
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