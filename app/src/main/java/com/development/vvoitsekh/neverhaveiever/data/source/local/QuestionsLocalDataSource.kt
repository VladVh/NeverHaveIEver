package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.database.Cursor
import android.os.Build
import android.util.Log
import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsDataSource
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry
import com.development.vvoitsekh.neverhaveiever.util.LocaleHelper
import java.util.*
import javax.inject.Inject


class QuestionsLocalDataSource @Inject constructor(var mDbHelper: QuestionsDbHelper): QuestionsDataSource {

    private val projection = arrayOf(
            QuestionsEntry.COLUMN_NAME_ROWID,
            QuestionsEntry.COLUMN_NAME_TEXT,
            QuestionsEntry.COLUMN_NAME_LEVEL)

    override fun getQuestions(modes: BooleanArray): Array<Question> {
        val db = mDbHelper.readableDatabase

        var selection = ""
        val selectionArgs = arrayListOf<String>()
        for (i in modes.indices)
        {
            val mode = modes[i]
            if (mode) {
                selection += QuestionsEntry.COLUMN_NAME_LEVEL + " = ? OR "
                selectionArgs.add((i + 1).toString())
            }
        }
        selection = selection.substring(0, selection.length - 3)
        
        val language = LocaleHelper.getLanguage(mDbHelper.context)
        val cursor:Cursor = if (language.equals("ua"))
            db.query(QuestionsEntry.TABLE_NAME_UA, projection, selection, selectionArgs.toTypedArray(), null, null, null)
        else
            db.query(QuestionsEntry.TABLE_NAME_EN, projection, selection, selectionArgs.toTypedArray(), null, null, null)

        val questions = ArrayList<Question>()
        cursor.moveToFirst()
        cursor.let {
            if (cursor.count > 0) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(QuestionsEntry.COLUMN_NAME_ROWID))
                    val text = cursor.getString(cursor.getColumnIndex(QuestionsEntry.COLUMN_NAME_TEXT))
                    val lvl = cursor.getInt(cursor.getColumnIndex(QuestionsEntry.COLUMN_NAME_LEVEL))
                    questions.add(Question(id, text, lvl))
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        return questions.toTypedArray()
    }
}