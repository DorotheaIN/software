package com.example.yike.service

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


object QuestionRepository {
    fun getQuestionList() = liveData(Dispatchers.IO) {
        val result = try {
            val questionList = Network.getQuestionList()
            if (questionList.status == "ok") {
                questionList.result
            } else {
                println("response status is not ok")
                null
            }
        } catch (e: Exception){
            println(e)
            null
        }
        emit(result)
    }
}

object ActivityRepository{
    fun getActivityList() = liveData(Dispatchers.IO){
        val result = try {
            val activityList = Network.getActivityList()
            if(activityList.status == "ok") {
                activityList.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e: Exception){
            println(e)
            null
        }
        emit(result)
    }
}