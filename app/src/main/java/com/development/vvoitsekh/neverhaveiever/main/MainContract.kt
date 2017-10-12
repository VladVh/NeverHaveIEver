package com.development.vvoitsekh.neverhaveiever.main

import com.development.vvoitsekh.neverhaveiever.BasePresenter
import com.development.vvoitsekh.neverhaveiever.BaseView


public interface MainContract {
    interface View : BaseView<Presenter> {
        fun showGame(modes: BooleanArray)
    }
    interface Presenter : BasePresenter {
        fun applyKidsMode()

        fun applyTeenagerMode()

        fun applyAdultMode()

        fun startGame()
    }
}