package com.development.vvoitsekh.neverhaveiever.data.source

import javax.inject.Inject


class QuestionsRepository @Inject constructor(dataSource: QuestionsDataSource) {

    private val mQuestionsDataSource: QuestionsDataSource = dataSource


}