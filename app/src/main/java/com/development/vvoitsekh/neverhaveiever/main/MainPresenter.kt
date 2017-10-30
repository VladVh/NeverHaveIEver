package com.development.vvoitsekh.neverhaveiever.main

import javax.inject.Inject


class MainPresenter @Inject constructor(view: MainContract.View) : MainContract.Presenter {

    private var mMainView: MainContract.View = view

    private var selectedModes = booleanArrayOf(false, false, false)

    override fun applyKidsMode() {
        selectedModes[0] = !selectedModes[0]
    }

    override fun applyTeenagerMode() {
        selectedModes[1] = !selectedModes[1]
    }

    override fun applyAdultMode() {
        selectedModes[2] = !selectedModes[2]
    }

    override fun startGame() {
        if (selectedModes.any { it -> it })
            mMainView.showGame(selectedModes)
    }
}