package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.TABLE_NAME
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.COLUMN_NAME_ID
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.COLUMN_NAME_TEXT
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.COLUMN_NAME_LEVEL
import javax.inject.Inject

class QuestionsDbHelper @Inject constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        val DATABASE_NAME = "Questions.db"
        val DATABASE_VERSION = 1
        val SQL_CREATE_ENTRIES =
                "CREATE TABLE $TABLE_NAME (" +
                        "$COLUMN_NAME_ID INTEGER PRIMARY KEY, " +
                        "$COLUMN_NAME_TEXT TEXT, " +
                        "$COLUMN_NAME_LEVEL INTEGER)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}