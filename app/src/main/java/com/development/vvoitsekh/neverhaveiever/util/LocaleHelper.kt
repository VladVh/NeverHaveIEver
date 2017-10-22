package com.development.vvoitsekh.neverhaveiever.util

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;


object LocaleHelper {

    private val SELECTED_LANGUAGE = "NeverIHaveEver.language"
    private lateinit var mContext:Context

    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    fun onAttach(context: Context, defaultLanguage: String): Context {
        val lang = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String? {
        return getPersistedData(context, Locale.getDefault().language)
    }

    fun setLocale(context: Context, language: String?): Context {
        persist(context, language)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)

    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

    private fun persist(context: Context, language: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()

        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String?): Context {
        val locale = Locale.forLanguageTag(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String?): Context {
        var context = context
        val config = context.resources.configuration
        val sysLocale: Locale?
        sysLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getSystemLocale(config)
        } else {
            getSystemLocaleLegacy(config)
        }
        if (!language.equals("") && sysLocale.language != language) {
            val locale:Locale = if(language.equals("en"))
                Locale("en",language)
            else
                Locale("uk",language)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale)
            } else {
                setSystemLocaleLegacy(config, locale)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config)
            } else {
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
            }
        }
        mContext = context
        return context
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//
//        val resources = context.getResources()
//
//        val configuration = resources.getConfiguration()
//        configuration.locale = locale
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            configuration.setLayoutDirection(locale)
//        }
//        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
//
//        return context
    }

    fun getSystemLocaleLegacy(config: Configuration): Locale = config.locale

    @TargetApi(Build.VERSION_CODES.N)
    fun getSystemLocale(config: Configuration): Locale = mContext.resources.configuration.locales.get(0)

    fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
        config.locale = locale
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun setSystemLocale(config: Configuration, locale: Locale) {
        config.setLocale(locale)
    }
}