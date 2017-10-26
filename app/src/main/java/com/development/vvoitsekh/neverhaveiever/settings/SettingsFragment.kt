package com.development.vvoitsekh.neverhaveiever.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import com.development.vvoitsekh.neverhaveiever.util.LocaleHelper
import com.development.vvoitsekh.neverhaveiever.R

class SettingsFragment: PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {
    val mNightPref = "NeverIHaveEver.night_mode"
    val mLangPref = "NeverIHaveEver.language"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        val langPref = findPreference("NeverIHaveEver.language") as ListPreference
        langPref.setValueIndex(langPref.findIndexOfValue(langPref.value))

        val rateBtn = findPreference("NeverIHaveEver.rate")
        rateBtn.setOnPreferenceClickListener {
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.packageName)))
            true
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals(mLangPref)) {
            val langPref = findPreference(key)
            val value = sharedPreferences?.getString(key, "")
            setPreferenceSummary(langPref, value!!)
            LocaleHelper.setLocale(activity, value)
            activity.recreate()
        }
        if (key.equals(mNightPref)) {
            val modePref = findPreference(key)
            val value = sharedPreferences?.getBoolean(key, false);
            setPreferenceSummary(modePref, value!!);
            activity.recreate()
        }
    }

    private fun setPreferenceSummary(preference: Preference, value: Any) {

        val stringValue = value.toString()

        if (preference is ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            val prefIndex = preference.findIndexOfValue(stringValue)
            //same code in one line
            //int prefIndex = ((ListPreference) preference).findIndexOfValue(value);

            //prefIndex must be is equal or garter than zero because
            //array count as 0 to ....
            if (prefIndex >= 0) {
                preference.summary = preference.entries[prefIndex]
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.summary = stringValue
        }
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
}