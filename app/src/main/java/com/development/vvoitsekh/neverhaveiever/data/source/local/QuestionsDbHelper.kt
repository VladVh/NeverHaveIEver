package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import javax.inject.Inject


class QuestionsDbHelper @Inject constructor(context: Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val context = context

    companion object {
        val DATABASE_NAME = "questionsDb.db"
        val DATABASE_VERSION = 1
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}