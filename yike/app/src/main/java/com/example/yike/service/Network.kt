package com.example.yike.service

import com.example.yike.viewModel.GlobalViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//Token的使用?

const val path: String = "DZY"

object Network {

    //service:
    private val userService = ServiceCreator.create<UserService>()

    //suspend fun:
    suspend fun fileUpload(requestBody: RequestBody) =
        userService.fileUpload(path, requestBody).await()

    suspend fun getLoginStatus(userEmail: String, passWord: String) =
        userService.getLoginStatus(userEmail, passWord).await()

    suspend fun getQuestionList() =
        userService.getQuestionList().await()

    suspend fun getQuestionByTheme() =
        userService.getQuestionByTheme().await()

    suspend fun getAnswerList(questionId: String) =
        userService.getAnswerList(questionId).await()

    suspend fun postQuestionStatus(questionId: String, userId: String) =
        userService.postQuestionStatus(questionId, userId).await()

    suspend fun checkQuestionStatus(questionId: String, userId: String) =
        userService.checkQuestionStatus(questionId, userId).await()


    suspend fun getActivityList() =
        userService.getActivityList().await()

    suspend fun getActivityDetail(activityID: Int) =
        userService.getActivityDetail(activityID).await()

    suspend fun getEvaluationList(activityID: Int) =
        userService.getEvaluationList(activityID).await()

    suspend fun getActivityRecommended(userID: String) =
        userService.getActivityRecommended(userID).await()

    suspend fun postLikeActivity(activityID: Int,userID: String) =
        userService.postLikeActivity(activityID,userID).await()

    suspend fun checkLike(activityID: Int,userID: String) =
        userService.checkLike(activityID,userID).await()

    suspend fun checkSubscribe(activityID: Int,userID: String) =
        userService.checkSubscribe(activityID,userID).await()

    suspend fun getOrganizationInfo(id:Int) =
        userService.getOrganizationInfo(id).await()

    suspend fun getActivityOfOrganization(id:Int) =
        userService.getActivityListByOrganization(id).await()

    suspend fun getOrgLoginStatus(id:Int,passWord: String) =
        userService.getOrgLoginStatus(id,passWord).await()

    suspend fun postDislikeActivity(activityID: Int,userID: String) =
        userService.postDislikeActivity(activityID,userID).await()

    suspend fun postSubActivity(activityID: Int,userID: String) =
        userService.postSubActivity(activityID,userID,GlobalViewModel.getToken()).await()

    suspend fun postDisSubActivity(activityID: Int,userID: String) =
        userService.postDisSubActivity(activityID,userID).await()

    suspend fun postPublishActivity(capacity:Int,content:String,date:String,form:String,genres:String,img:String,
                                    intro:String,organizationID: Int,place:String,status:Int,title:String) =
        userService.postPubActivity(capacity,content,date,form,genres,img,intro,organizationID,place,status,title).await()

    suspend fun postCorrectActivity(id:Int,capacity:Int,content:String,date:String,form:String,genres:String,img:String,
                                    intro:String,organizationID: Int,place:String,status:Int,title:String) =
        userService.postUpdateActivity(id,capacity,content,date,form,genres,img,intro,organizationID,place,status,title).await()

    suspend fun postDelActivity(id:Int) =
        userService.postDeleteActivity(id).await()

    suspend fun getEvaluationAnalysis(id: Int) =
        userService.getReviewAnalysis(id).await()

    suspend fun getSubscriberList(id: Int) =
        userService.getSubscriberList(id).await()

    suspend fun postReviewActivity(activityID: Int,content: String,userID: String,score:Int) =
        userService.postReviewActivity(activityID,content,userID, score).await()

    suspend fun postDelReviewActivity(activityID: Int,userID: String) =
        userService.postDeleteActivityReview(activityID, userID).await()

    suspend fun getPublishQuestionList(id:String) =
        userService.getPublishQuestionList(id).await()

    suspend fun getFollowQuestionList(id:String) =
        userService.getFollowQuestionsList(id).await()

    suspend fun sendEmail(email:String) =
        userService.sendEmail(email).await()

    suspend fun getPersonRegister(email:String,name:String,password: String) =
        userService.getPersonRegister(email,name,password).await()

    suspend fun getAllCommentByQuestionIdAndAnswerId(answerId:Int,questionId:Int) =
        userService.getAllCommentByQuestionIdAndAnswerId(answerId,questionId).await()

    suspend fun addAnswer(content:String,questionId: String,userId: String) =
        userService.addAnswer(content,questionId,userId).await()

    suspend fun comment(answerId: String,content:String,userId: String) =
        userService.comment(answerId,content,userId).await()

    suspend fun getMyActivities(email: String) =
        userService.getMyActivities(email).await()

    suspend fun officialRegister(avator:String,certification:String,introduction:String,password:String,userName:String) =
        userService.officialRegister(avator, certification, introduction, password, userName).await()

    suspend fun addQuestion(content: String,title:String,userId: String) =
        userService.addQuestion(content, title, userId).await()

    suspend fun filterActivity(genres: String,subState:String,key:String,status:String) =
        userService.getFilteredActivity(genres,subState,key,status).await()

    suspend fun getApplications() =
        userService.getApplications().await()

    suspend fun updateOUserStatus(ID:String,flag:String) =
        userService.updateOUserStatus(ID,flag).await()

    suspend fun postApplyResult(content:String,title:String,to:String)=
        userService.postApplyResult(content, title, to).await()

    suspend fun adminLogin(ID:String,password: String)=
        userService.adminLogin(ID, password).await()

    suspend fun getReports()=
        userService.getReports().await()

    suspend fun updateIUserStatus(ID: String,flag: String)=
        userService.updateIUserStatus(ID, flag).await()

    suspend fun reportUser(rID:String,reason:String,wID:String)=
        userService.reportUser(rID, reason, wID).await()

    suspend fun verifyCode(inputCode: String)=
        userService.verifyCode(inputCode).await()


    //为call添加扩展函数 await
    //这样所有返回call的函数都可以调用之
    private suspend fun <T> Call<T>.await() :T {
        return suspendCoroutine { continuation -> //传入调用者的上下文
            enqueue(object : Callback<T> { //开启retrofit请求
                //重载回调部分
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    println(response)
                    if (body != null) continuation.resume(body) // 如果body合法 则恢复协程 返回body
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    println(t)
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}
