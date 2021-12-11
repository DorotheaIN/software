package com.example.yike.service

import androidx.lifecycle.liveData
import com.example.yike.model.CheckResponse
import com.example.yike.model.QuestionResponse
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.Organization
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
        }catch (e: Exception){
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
    fun postLikeActivity(activityID: Int,userID: String,status:Int) = liveData(Dispatchers.IO){
        val result = try {
            val likeResult = if(status == 1){
                Network.postLikeActivity(activityID,userID)
            }else{
                Network.postDislikeActivity(activityID,userID)
            }
            if(likeResult.code == 200) {
                true
            } else {
                println("response status is not ok!")
                println(likeResult)
                false
            }
        } catch (e: Exception){
            println(e)
            false
        }
        emit(result)
    }

    fun postSubActivity(activityID: Int,userID: String,status:Int) = liveData(Dispatchers.IO){
        val result = try {
            val subResult = if(status == 1){
                Network.postSubActivity(activityID,userID)
            }else{
                Network.postDisSubActivity(activityID, userID)
            }
            if(subResult.code == 200){
                true
            }else {
                println("response status is not ok!")
                false
            }
        } catch (e: Exception){
            println(e)
            false
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
    fun getActivityByOrganization(id:Int) = liveData(Dispatchers.IO){
        val result = try {
            val activityList = Network.getActivityOfOrganization(id)
            if(activityList.code == 200) {
                activityList.result
            } else {
                println("response status is not ok!")
                ArrayList<Activity>()
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            ArrayList<Activity>()
        }
        emit(result)
    }

    fun publishActivity(capacity:Int,content:String, date:String, form:String, genres:String, img:String, intro:String, organizationID: Int, place:String, status:Int, title:String) = liveData(Dispatchers.IO){
        val result = try {
            val res = Network.postPublishActivity(capacity,content,date,form,genres,img,intro,organizationID,place,status,title)
            if(res.code == 200) {
                true
            } else {
                println("response status is not ok!")
                false
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            false
        }
        emit(result)
    }

    fun correctActivity(id: Int,capacity:Int,content:String, date:String, form:String, genres:String, img:String, intro:String, organizationID: Int, place:String, status:Int, title:String) = liveData(Dispatchers.IO){
        val result = try {
            val res = Network.postCorrectActivity(id,capacity,content,date,form,genres,img,intro,organizationID,place,status,title)
            if(res.code == 200) {
                true
            } else {
                println("response status is not ok!")
                false
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            false
        }
        emit(result)
    }

    fun deleteActivity(id:Int) = liveData(Dispatchers.IO) {
        val result = try {
            val res = Network.postDelActivity(id)
            if(res.code == 200) {
                true
            } else {
                println("response status is not ok!")
                false
            }
        } catch (e: Exception){
            println(e)
            println("yuzhierrr")
            false
        }
        emit(result)
    }

    fun getEvaAnalysis(id: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val analysisRes = Network.getEvaluationAnalysis(id)
            if(analysisRes.code == 200){
                analysisRes.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e:Exception){
            println(e)
            null
        }
        emit(result)
    }

    fun getSubscriberList(id: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val subscriberList = Network.getSubscriberList(id)
            if(subscriberList.code == 200){
                subscriberList.result
            } else {
                println("response status is not ok!")
                null
            }
        } catch (e:Exception){
            println(e)
            null
        }
        emit(result)
    }
}

object OrgLoginRepository{
    fun checkLoginStatus(id: Int, passWord: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginResponse = Network.getOrgLoginStatus(id, passWord)
            if (loginResponse.code == 200) {
                Organization(loginResponse.result.id,loginResponse.result.status,loginResponse.result.avator,loginResponse.result.username,loginResponse.result.introduction)
            } else {
                println("response code is ${loginResponse.code} error msg is ${loginResponse.msg}")
                val s = when(loginResponse.msg) {
                    "unregister" -> -2
                    else -> -1
                }
                Organization(-1,status = s,"","","")
            }
        } catch (e: Exception){
            println(e)
            Organization(-1,status = 0,"","","")
        }
        emit(result)
    }
}


