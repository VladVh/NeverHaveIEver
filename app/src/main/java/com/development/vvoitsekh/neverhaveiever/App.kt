package com.development.vvoitsekh.neverhaveiever

import android.app.Activity
import android.app.Application
import android.content.Context
import com.development.vvoitsekh.neverhaveiever.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.development.vvoitsekh.neverhaveiever.util.LocaleHelper
import com.development.vvoitsekh.neverhaveiever.util.TypefaceUtil


class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
               // .create()
                .inject(this)
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Veles-Regular.0.9.2.otf");
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }
}