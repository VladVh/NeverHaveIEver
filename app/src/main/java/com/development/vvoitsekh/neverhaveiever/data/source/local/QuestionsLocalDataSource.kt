package com.development.vvoitsekh.neverhaveiever.data.source.local

import com.development.vvoitsekh.neverhaveiever.data.Question
import com.development.vvoitsekh.neverhaveiever.data.source.QuestionsDataSource
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry
import javax.inject.Inject


class QuestionsLocalDataSource @Inject constructor(mDbHelper: QuestionsDbHelper): QuestionsDataSource {

    var mDbHelper: QuestionsDbHelper = mDbHelper

    override fun getQuestions(modes: BooleanArray): Array<Question> {
        //mDbHelper.createDataBase()
        val db = mDbHelper.readableDatabase;

        val projection = arrayOf(
                "rowid",
                QuestionsEntry.COLUMN_NAME_TEXT,
                QuestionsEntry.COLUMN_NAME_LEVEL)
        var selection = "";
        val selectionArgs = arrayListOf<String>()
        for (i in modes.indices)
        {
            var mode = modes[i];
            if (mode) {
                selection += QuestionsEntry.COLUMN_NAME_LEVEL + " = ? OR "
                selectionArgs.add((i + 1).toString())
            }
        }
        selection = selection.substring(0, selection.length - 3);
        //val selection = QuestionsEntry.COLUMN_NAME_LEVEL + " = ? OR " + QuestionsEntry.COLUMN_NAME_LEVEL + " = ?"
        //val selectionArgs: Array<String> = arrayOf(level.toString(), (level - 1).toString())
        val cursor = db.query(QuestionsEntry.TABLE_NAME, projection, selection, selectionArgs.toTypedArray(), null, null, null)
        //val cursor = db.rawQuery("SELECT * FROM questions_en", null)
        val questions = ArrayList<Question>()
        cursor.moveToFirst()
        cursor.let {
            if (cursor.count > 0) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("rowid"))
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