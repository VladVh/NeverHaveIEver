package com.development.vvoitsekh.neverhaveiever.util

import android.content.Context
import android.preference.PreferenceManager


object PrefUtil {

    fun isNightModeOn(context: Context): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean("NeverIHaveEver.night_mode", false)
    }
}