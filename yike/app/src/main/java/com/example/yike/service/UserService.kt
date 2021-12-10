package com.example.yike.service

import com.example.yike.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("login")//path看swagger
    fun getLoginStatus(@Query("u") user: String, @Query("p") passWord: String): Call<LoginResponse>

    @GET("discuss")//path看swagger
    fun getQuestionList(): Call<QuestionResponse>

    @GET("getAllActivities")//获取所有活动
    fun getActivityList(): Call<ActivityResponse>

    @GET("getActivityDetail")
    fun getActivityDetail(@Query("ID") activityID: Int):Call<ActivityDetailResponse>

    @GET("getReviewsByActivity")
    fun getEvaluationList(@Query("ID") activityID: Int):Call<EvaluationListResponse>

    @GET("getActivityListByOrganization")
    fun getActivityListByOrganization(@Query("o") organizationID: Int):Call<ActivityResponse>

    @GET("getRecommendActivity")
    fun getActivityRecommended(@Query("ID") userID: String) :Call<ActivityResponse>

    @POST("likeActivity")
    @Multipart
    fun postLikeActivity(@Query("activityID") activityID:Int,@Query("individualUserID") userID:String): Call<LikeResponse>

}