package com.development.vvoitsekh.neverhaveiever.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.TABLE_NAME
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.COLUMN_NAME_TEXT
import com.development.vvoitsekh.neverhaveiever.data.source.local.QuestionsPersistenceContract.QuestionsEntry.COLUMN_NAME_LEVEL
import javax.inject.Inject
import android.database.sqlite.SQLiteException
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import java.io.FileOutputStream


class QuestionsDbHelper @Inject constructor(context: Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val CONTEXT = context

    companion object {
        val DATABASE_NAME = "questionsDb.db"
        val DATABASE_PATH = "/data/user/0/com.development.vvoitsekh.neverhaveiever/databases/"
        val DATABASE_VERSION = 1
        val SQL_CREATE_ENTRIES =
                "CREATE TABLE $TABLE_NAME (" +
                        "$COLUMN_NAME_TEXT TEXT NOT NULL, " +
                        "$COLUMN_NAME_LEVEL INTEGER NOT NULL)"
    }

    fun createDataBase() {

        val dbExist = checkDataBase()

        if (dbExist) {
            //do nothing - database already exist
        //} else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.readableDatabase

            try {

                copyDataBase()

            } catch (e: Exception) {

                throw Error("Error copying database")

            }

        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private fun checkDataBase(): Boolean {

        var checkDB: SQLiteDatabase? = null

        try {
            val myPath = DATABASE_PATH + DATABASE_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

        } catch (e: SQLiteException) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close()

        }

        return if (checkDB != null) true else false
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private fun copyDataBase() {

        //Open your local db as the input stream
        val myInput = CONTEXT.getAssets().open("databases\\" + DATABASE_NAME)

        // Path to the just created empty db
        val outFileName = DATABASE_PATH + DATABASE_NAME

        //Open the empty db as the output stream
        val myOutput = FileOutputStream(outFileName)

        //transfer bytes from the inputfile to the outputfile
        val buffer = ByteArray(1024)
        var length: Int
        length = myInput.read(buffer)
        while (length > 0) {
            myOutput.write(buffer, 0, length)
            length = myInput.read(buffer)
        }

        //Close the streams
        myOutput.flush()
        myOutput.close()
        myInput.close()

    }


    fun openDataBase() {

        //Open the database
        val myPath = DATABASE_PATH + DATABASE_NAME
        val myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}