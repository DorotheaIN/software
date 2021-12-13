package com.example.yike.service

import com.example.yike.model.*
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
//    @GET("login")
//    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>
//
//    @GET("discuss")
//    fun getQuestionList(): Call<QuestionResponse>
//
//    @GET("recommenddis")
//    fun getQuestionByTheme(): Call<QThemeResponse>
//
//    @GET("answer")
//    fun getAnswerList(@Query("questionId") questionId: String): Call<AnswerResponse>

    @POST("discuss/takeAntiFocusQuestion")
    fun postQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<PostResponse>

    @GET("discuss/checkFocusQuestion")
    fun checkQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<CheckResponse>

    @GET("login")
    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>

    @GET("discuss/getQuestionWithFollowNumAndLikeNum")
    fun getQuestionList(): Call<QuestionResponse>

    @GET("discuss/getQuestionWithFollowNumAndLikeNumAndAvatar")
    fun getQuestionByTheme(): Call<QThemeResponse>

    @POST("discuss/getAllAnswerByQuestionId")
    fun getAnswerList(@Query("questionId") questionId: String): Call<AnswerResponse>


    @GET("getAllActivities")//获取所有活动
    fun getActivityList(): Call<ActivityResponse>

    @GET("getActivityDetail")
    fun getActivityDetail(@Query("ID") activityID: Int):Call<ActivityDetailResponse>

    @GET("getReviewsByActivity")
    fun getEvaluationList(@Query("ID") activityID: Int):Call<EvaluationListResponse>

    @GET("getRecommendActivity")
    fun getActivityRecommended(@Query("ID") userID: String) :Call<ActivityResponse>

    @POST("likeActivity")
    fun postLikeActivity(@Query("activityID") activityID:Int,@Query("individualUserID") userID:String):Call<CheckResponse>

    @GET("checkLike")
    fun checkLike(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String):Call<CheckResponse>

    @GET("checkSignUp")
    fun checkSubscribe(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String):Call<CheckResponse>

    @GET("getOrganizationInfo")
    fun getOrganizationInfo(@Query("ID") id:Int):Call<OrganizationResponse>

    @GET("getAllActivityByOrg")
    fun getActivityListByOrganization(@Query("ID") organizationID: Int):Call<ActivityResponse>

    @GET("getMyFocusQuestions")
    fun getFollowQuestionsList(@Query("ID")id:String):Call<FollowQuestionResponse>

    @GET("getMyQuestions")
    fun getPublishQuestionList(@Query("ID") id:String):Call<PublishQuestionResponse>

    @POST("sendEmail")
    fun sendEmail(@Query("to")email:String):Call<SendEmailResponse>

    @POST("signUp")
    fun getPersonRegister(@Query("email")email:String,@Query("name")name:String,@Query("password")password:String):Call<getPersonRegistResponse>

    @GET("discuss/getAllCommentByQuestionIdAndAnswerId")
    fun getAllCommentByQuestionIdAndAnswerId(@Query("answerId")answerId:Int,@Query("questionId")questionId:Int):Call<GetAllCommentByQuestionIdAndAnswerIdResponse>

    @POST("discuss/addAnswer")
    fun addAnswer(@Query("content")content:String,@Query("questionId")questionId: String,@Query("userId")userId: String):Call<AddAnswerResponse>

    @POST("discuss/addComment")
    fun comment(@Query("answerId")answerId: String,@Query("content")content: String,@Query("userId")userID: String):Call<CommentResponse>

    @GET("getMyActivities")
    fun getMyActivities(@Query("email") email: String):Call<ActivityResponse>

    @GET("signUp")
    fun officialRegister(@Query("avator")avator:String,@Query("certification")certification:String,@Query("introduction")introduction:String,@Query("password")password:String,@Query("userName")userName:String):Call<OfficialRegisterResponse>

}

//getAllCommentByQuestionIdAndAnswerId