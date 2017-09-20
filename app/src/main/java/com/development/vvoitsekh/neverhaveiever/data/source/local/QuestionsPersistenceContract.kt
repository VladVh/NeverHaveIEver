package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.provider.BaseColumns

object QuestionsPersistenceContract {

    object QuestionsEntry : BaseColumns {
        val TABLE_NAME = "questions"
        val COLUMN_NAME_ID = "id"
        val COLUMN_NAME_TEXT = "text"
        val COLUMN_NAME_LEVEL = "level"
    }
}