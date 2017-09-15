package com.development.vvoitsekh.neverhaveiever.main

/**
 * Created by v.voitsekh on 14.09.2017.
 */
class MainPresenter : MainContract.Presenter {

    private lateinit var mMainView: MainContract.View

    override fun startKidsGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startNormalGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startAdultsGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start(view: MainContract.View) {
        mMainView = view;
    }
}