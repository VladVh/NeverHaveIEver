package com.development.vvoitsekh.neverhaveiever

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.development.vvoitsekh.neverhaveiever.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import android.preference.PreferenceManager
import android.content.SharedPreferences
import java.util.*


class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
               // .create()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }
}