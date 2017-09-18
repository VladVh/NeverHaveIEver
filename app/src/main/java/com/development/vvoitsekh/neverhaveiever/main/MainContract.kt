package com.development.vvoitsekh.neverhaveiever.main

import com.development.vvoitsekh.neverhaveiever.BasePresenter
import com.development.vvoitsekh.neverhaveiever.BaseView


public interface MainContract {
    interface View : BaseView<Presenter> {
        fun showGame(level: Int)
    }
    interface Presenter : BasePresenter {
        fun startKidsGame()

        fun startNormalGame()

        fun startAdultsGame()
    }
}