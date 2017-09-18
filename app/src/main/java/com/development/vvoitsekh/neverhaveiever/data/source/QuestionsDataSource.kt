package com.development.vvoitsekh.neverhaveiever.data.source

import javax.inject.Inject


class QuestionsDataSource @Inject constructor(dbHelper: QuestionsDbHelper) {

    private var mDbHelper: QuestionsDbHelper = dbHelper
}