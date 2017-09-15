package com.development.vvoitsekh.neverhaveiever.main

import dagger.Module
import dagger.Provides

/**
 * Created by v.voitsekh on 13.09.2017.
 */
@Module
class MainModule {

    @Provides
    fun provideMainPresenter() = MainPresenter()
}