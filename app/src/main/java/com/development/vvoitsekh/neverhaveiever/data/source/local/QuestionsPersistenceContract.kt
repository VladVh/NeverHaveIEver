package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.provider.BaseColumns

object QuestionsPersistenceContract {

    object QuestionsEntry : BaseColumns {
        val TABLE_NAME = "questions_ua"
        val TABLE_NAME_UA = "questions_ua"
        val TABLE_NAME_EN = "questions_en"
        val COLUMN_NAME_TEXT = "text"
        val COLUMN_NAME_LEVEL = "level"
    }
}