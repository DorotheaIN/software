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
}

object AnswerRepository {
    fun getAnswerList() = liveData(Dispatchers.IO) {
        val result = try {
            val answerList = Network.getAnswerList()
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
            val likeresult = Network.postLikeActivity(activityID,userID)
            println(likeresult.code)
            if(likeresult.code == 200) {
                true
            } else {
                println("response status is not ok!")
                println(likeresult)
                null
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            null
        }
        emit(result)
    }

    fun checkLike(activityID: Int,userID: String) = liveData(Dispatchers.IO){
        val result = try {
            val result = Network.checkLike(activityID,userID)
            if(result.code == 200) {
                if(result.result == 1){
                    true
                }else{
                    false
                }
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

    fun checkSubscribe(activityID: Int,userID: String) = liveData(Dispatchers.IO){
        val result = try {
            val result = Network.checkSubscribe(activityID,userID)
            if(result.code == 200) {
                if(result.result == 1){
                    true
                }else{
                    false
                }
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

object OrganizationRepository{
    fun getOrganizationInfo(id:Int) = liveData(Dispatchers.IO){
        val result = try {
            val organizationInfo = Network.getOrganizationInfo(id)
            if(organizationInfo.code == 200) {
                organizationInfo.result
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

    fun getActivityByOrganization(id:Int) = liveData(Dispatchers.IO){
        val result = try {
            val activityList = Network.getActivityOfOrganization(id)
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


