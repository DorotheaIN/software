package com.example.yike.service

import androidx.lifecycle.liveData
import com.example.yike.model.QuestionResponse
import com.example.yike.viewModel.Question
import com.example.yike.viewModel.UserInfo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

//repo中函函数名与VM中相同 表用户方法
//network和接口中定义的函数名相同 通过get post来反应请求类型

object LoginRepository {
    fun checkLoginStatus(userEmail: String, passWord: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginResponse = Network.getLoginStatus(userEmail, passWord)
            if (loginResponse.code == 200) {
                UserInfo(loginResponse.result.id, loginResponse.result.user_NAME, loginResponse.result.status,
                    loginResponse.result.introduction, loginResponse.result.avator)
            } else {
                println("response code is ${loginResponse.code} error msg is ${loginResponse.msg}")
                val s = when(loginResponse.msg) {
                    "unregister" -> -2
                    else -> -1
                }
                UserInfo(status = s)
            }
        } catch (e: Exception){
            println(e)
            UserInfo()
        }
        emit(result)
    }
}

object QuestionRepository {
    fun getQuestionList() = liveData(Dispatchers.IO) {
        val result = try {
            val questionList = Network.getQuestionList()
            if (questionList.code == 200) {
                questionList.result
            } else {
                println("response code is ${questionList.code} error msg is ${questionList.msg}")
                null
            }
        } catch (e: Exception){
            println(e)
            null
        }
        emit(result)
    }
    fun getQuestionByTheme() = liveData(Dispatchers.IO) {
        val result = try {
            val qThemeList = Network.getQuestionByTheme()
            if (qThemeList.code == 200) {
                qThemeList.result
            } else {
                println("response code is ${qThemeList.code} error msg is ${qThemeList.msg}")
                null
            }
        } catch (e: Exception){
            println(e)
            null
        }
        emit(result)
    }
    fun postQuestionStatus(questionId: String, userId: String) = liveData(Dispatchers.IO) {
        val result = try {
            val r = Network.postQuestionStatus(questionId, userId)
            if (r.code == 200) {
                r.result
            } else {
                println("response code is ${r.code} error msg is ${r.msg}")
                false
            }
        } catch (e: Exception){
            println(e)
            false
        }
        emit(result)
    }
    fun checkQuestionStatus(questionId: String, userId: String) = liveData(Dispatchers.IO) {
        val result = try {
            val status = Network.checkQuestionStatus(questionId, userId)
            if (status.code == 200) {
                status.result
            } else {
                println("response code is ${status.code} error msg is ${status.msg}")
                -1
            }
        } catch (e: Exception){
            println(e)
            -1
        }
        emit(result)
    }
}

object AnswerRepository {
    fun getAnswerList(questionId: String) = liveData(Dispatchers.IO) {
        val result = try {
            val answerList = Network.getAnswerList(questionId)
            if (answerList.code == 200) {
                answerList.result
            } else {
                println("response status is ${answerList.code} error msg is ${answerList.msg}")
                null
            }
        } catch (e: Exception){
            println(e)
            null
        }
        emit(result)
    }
}



