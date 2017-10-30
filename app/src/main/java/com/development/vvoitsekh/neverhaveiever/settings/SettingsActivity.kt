package com.development.vvoitsekh.neverhaveiever.settings

import android.os.Bundle
import com.development.vvoitsekh.neverhaveiever.BaseActivity


class SettingsActivity: BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
    }
}