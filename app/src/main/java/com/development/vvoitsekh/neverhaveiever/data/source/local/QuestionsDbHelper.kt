package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import javax.inject.Inject


class QuestionsDbHelper : SQLiteAssetHelper {
    @Inject constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION) {
        this.context = context
    }

    val context:Context


    companion object {
        val DATABASE_NAME = "questionsDb.db"
        val DATABASE_VERSION = 1
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}