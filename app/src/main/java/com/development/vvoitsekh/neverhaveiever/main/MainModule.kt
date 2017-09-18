package com.development.vvoitsekh.neverhaveiever.main

import dagger.Binds
import dagger.Module


@Module
abstract class MainModule {

    @Binds
    abstract fun view(activity: MainActivity): MainContract.View

    @Binds
    abstract fun bindMainPresenter(presenter: MainPresenter): MainContract.Presenter
}