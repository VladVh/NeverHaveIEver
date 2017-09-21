package com.development.vvoitsekh.neverhaveiever.data.source.local

import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsDataSource
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry
import javax.inject.Inject


class QuestionsLocalDataSource @Inject constructor(mDbHelper: QuestionsDbHelper): QuestionsDataSource {

    var mDbHelper: QuestionsDbHelper = mDbHelper

    override fun getQuestions(level: Int): Array<Question> {
        val db = mDbHelper.readableDatabase;

        val projection = arrayOf(QuestionsEntry.COLUMN_NAME_ID,
                QuestionsEntry.COLUMN_NAME_TEXT,
                QuestionsEntry.COLUMN_NAME_LEVEL)
        val selection = QuestionsEntry.COLUMN_NAME_LEVEL + " = ? OR " + QuestionsEntry.COLUMN_NAME_LEVEL + " = ?"
        val selectionArgs: Array<String> = arrayOf(level.toString(), (level - 1).toString())
        val cursor = db.query(QuestionsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null)

        val questions = ArrayList<Question>()
        cursor.let {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val text = cursor.getString(cursor.getColumnIndex(QuestionsEntry.COLUMN_NAME_TEXT))
                    val lvl = cursor.getInt(cursor.getColumnIndex(QuestionsEntry.COLUMN_NAME_LEVEL))
                    questions.add(Question(text, lvl))
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        return questions.toTypedArray()
    }
}