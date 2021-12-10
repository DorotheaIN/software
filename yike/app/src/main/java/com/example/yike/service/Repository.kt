package com.example.yike.service

import androidx.lifecycle.liveData
import com.example.yike.viewModel.UserInfo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


object LoginRepository {
    fun checkLoginStatus(userName: String, passWord: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginResponse = Network.getLoginStatus(userName, passWord)
//            val testResponse = Network.getTest()
            if (loginResponse.code == 200) {
                UserInfo(loginResponse.result.userId, loginResponse.result.userName,
                    loginResponse.result.userStatus)
//                UserInfo(testResponse.result, "1", "user")
            } else {
                println("response status is not ok")
                UserInfo()
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
//    fun getActivityList() = liveData(Dispatchers.IO){
//        val result = try {
//            val activityList = Network.getActivityList()
//            if(activityList.status == "ok") {
//                activityList.result
//            } else {
//                println("response status is not ok!")
//                null
//            }
//        } catch (e: Exception){
//            println(e)
//            println("yuzhierrrrr")
//            null
//        }
//        emit(result)
//    }
    fun getActivityList() = liveData(Dispatchers.IO){
        val result = try {
            val activityList = Network.getActivityList()
            if(activityList.code == 200) {
                activityList.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            null
        }
        emit(result)
    }

//    fun getActivityDetail(activityID:Int) = liveData(Dispatchers.IO){
//        val result = try {
//            val activityDetail = Network.getActivityDetail(activityID)
//            if (activityDetail.status == "ok") {
//                activityDetail.result
//            } else {
//                println("response status is not ok!")
//                null
//            }
//        } catch (e: Exception){
//            println(e)
//            println("yuzhierrrrr")
//            null
//        }
//        emit(result)
//    }
    fun getActivityDetail(activityID:Int) = liveData(Dispatchers.IO){
        val result = try {
            val activityDetail = Network.getActivityDetail(activityID)
            if (activityDetail.code == 200) {
                activityDetail.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrrrr")
            null
        }
        emit(result)
    }


//    fun getEvaluationList(activityID:Int) = liveData(Dispatchers.IO){
//        val result = try {
//            val evaluationList = Network.getEvaluationList(activityID)
//            if (evaluationList.status == "ok") {
//                evaluationList.result
//            } else {
//                println("response status is not ok!")
//                null
//            }
//        } catch (e: Exception){
//            println(e)
//            println("yuzhierrrrr")
//            null
//        }
//        emit(result)
//    }
    fun getEvaluationList(activityID:Int) = liveData(Dispatchers.IO){
        val result = try {
            val evaluationList = Network.getEvaluationList(activityID)
            if (evaluationList.code == 200) {
                evaluationList.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrrrr")
            null
        }
        emit(result)
    }

    fun getActivityRecommended(userID: String) = liveData(Dispatchers.IO){
        val result = try {
            val activityList = Network.getActivityList()
            if(activityList.code == 200) {
                activityList.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            null
        }
        emit(result)
    }

}

object UserActivityRepository {
    fun postLikeActivity(activityID: Int,userID: String) = liveData(Dispatchers.IO){
        val result = try {
            val result = Network.postLikeActivity(activityID,userID)
            if(result.code == 200) {
                true
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            null
        }
        emit(result)
    }
}