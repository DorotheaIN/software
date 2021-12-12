package com.example.yike.service

import androidx.lifecycle.liveData
import com.example.yike.model.QuestionResponse
import com.example.yike.viewModel.QuesAnswer
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

object PublishQuestionRepository{
    fun getPublishQuestion(id:String) = liveData(Dispatchers.IO){
        val result = try {
            val publishQuestionList = Network.getPublishQuestionList(id)
            if(publishQuestionList.code == 200) {
                publishQuestionList.result
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

object FollowQuestionRepository{
    fun getFollowQuestion(id:String) = liveData(Dispatchers.IO){
        val result = try {
            val followQuestionList = Network.getFollowQuestionList(id)
            if(followQuestionList.code == 200) {
                followQuestionList.result
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

object SendEmailRepository {
    fun sendEmail(email:String) = liveData(Dispatchers.IO) {
        val result = try {
            val sendEmailResponse = Network.sendEmail(email)
            println(sendEmailResponse.result)
            if (sendEmailResponse.code == 200) {
//                println("111111111111111111")
//                println(sendEmailResponse.result)
                sendEmailResponse.result
            } else {
                println("response status is not ok!")
                ""
                }
        } catch (e: Exception){
            println(e)
            UserInfo()
            ""
        }
        emit(result)
    }
}

object GetPersonRegisterRepository {
    fun getPersonRegister(email:String,name:String,password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val getPersonRegisterResponse = Network.getPersonRegister(email,name,password)
            if (getPersonRegisterResponse.code == 200) {
                getPersonRegisterResponse.result
            } else {
                println("response status is not ok!")
            }
            ""
        } catch (e: Exception){
            println(e)
            UserInfo()
            ""
        }
        emit(result)
    }
}

object GetAllCommentByQuestionIdAndAnswerIdRepository {
    fun getAllCommentByQuestionIdAndAnswerId(answerId:String,questionId: String) = liveData(Dispatchers.IO) {
        val result = try {
            println("c22222222222222222222222222222222")
            println(answerId)
            println(questionId)
            val intAnswer=answerId.toInt()
            val intQuestion=questionId.toInt()
            println(intAnswer.toString())
            println(intQuestion.toString())
            val getAllCommentByQuestionIdAndAnswerIdResponse = Network.getAllCommentByQuestionIdAndAnswerId(intAnswer,intQuestion)
            if (getAllCommentByQuestionIdAndAnswerIdResponse.code == 200) {
                println("c3333333333333333333")
                println(answerId)
                println(getAllCommentByQuestionIdAndAnswerIdResponse.result.question)
                getAllCommentByQuestionIdAndAnswerIdResponse.result
            } else {
                println("response status is not ok!")
//                QuesAnswer()
                null
            }
        } catch (e: Exception){
            println(e)
//            QuesAnswer()
            null
        }
        emit(result)
    }
}
