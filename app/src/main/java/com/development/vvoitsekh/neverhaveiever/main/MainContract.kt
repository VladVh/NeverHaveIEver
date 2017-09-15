package com.development.vvoitsekh.neverhaveiever.main

import com.development.vvoitsekh.neverhaveiever.BasePresenter
import com.development.vvoitsekh.neverhaveiever.BaseView

/**
 * Created by v.voitsekh on 14.09.2017.
 */
public interface MainContract {
    interface View : BaseView<Presenter> {
        fun showGame(level: Int)
    }
    interface Presenter : BasePresenter {
        fun startKidsGame()

        fun startNormalGame()

        fun startAdultsGame()

        fun start(view: View)
    }
}