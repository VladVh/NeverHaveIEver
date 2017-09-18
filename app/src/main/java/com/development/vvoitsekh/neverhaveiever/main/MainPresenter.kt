package com.development.vvoitsekh.neverhaveiever.main

import javax.inject.Inject


class MainPresenter @Inject constructor(view: MainContract.View) : MainContract.Presenter {

    private var mMainView: MainContract.View = view

    override fun startKidsGame() {
        mMainView.showGame(1)
    }

    override fun startNormalGame() {
        mMainView.showGame(2)
    }

    override fun startAdultsGame() {
        mMainView.showGame(3)
    }
}