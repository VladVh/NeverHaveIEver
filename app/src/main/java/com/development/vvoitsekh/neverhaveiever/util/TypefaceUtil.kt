package com.development.vvoitsekh.neverhaveiever.util

import android.content.Context
import java.lang.reflect.AccessibleObject.setAccessible
import android.graphics.Typeface
import android.util.Log


/**
 * Created by v.voitsekh on 06.10.2017.
 */
object TypefaceUtil {

    /**
     * Using reflection to override default typeface
     * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
     * @param context to work with assets
     * @param defaultFontNameToOverride for example "monospace"
     * @param customFontFileNameInAssets file name of the font from assets
     */
    fun overrideFont(context: Context, defaultFontNameToOverride: String, customFontFileNameInAssets: String) {
        try {
            val customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets)

            val defaultFontTypefaceField = Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
            defaultFontTypefaceField.isAccessible = true
            defaultFontTypefaceField.set(null, customFontTypeface)
        } catch (e: Exception) {
            Log.e("Custom font error", "Can not set custom font $customFontFileNameInAssets instead of $defaultFontNameToOverride")
        }

    }
}