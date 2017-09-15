package com.development.vvoitsekh.neverhaveiever

import android.app.Activity
import android.app.Application
import com.development.vvoitsekh.neverhaveiever.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by v.voitsekh on 13.09.2017.
 */
class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .create()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}