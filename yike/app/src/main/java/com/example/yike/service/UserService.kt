package com.example.yike.service

import com.example.yike.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("login")
    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>

    @GET("discuss")
    fun getQuestionList(): Call<QuestionResponse>

    @GET("recommenddis")
    fun getQuestionByTheme(): Call<QThemeResponse>

    @GET("answer")
    fun getAnswerList(@Query("questionId") questionId: String): Call<AnswerResponse>

    @GET("followQuestion")
    fun postQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<PostResponse>

    @GET("checkQuestion")
    fun checkQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<CheckResponse>

//    @GET("login")
//    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>
//
//    @GET("discuss/getQuestionWithFollowNumAndLikeNum")
//    fun getQuestionList(): Call<QuestionResponse>
//
//    @GET("discuss/getQuestionWithFollowNumAndLikeNumAndAvatar")
//    fun getQuestionByTheme(): Call<QThemeResponse>
//
//    @POST("discuss/getAllAnswerByQuestionId")
//    fun getAnswerList(@Query("questionId") questionId: String): Call<AnswerResponse>

    @GET("/test/doLogin")
    fun getTest(): Call<TestResponse>
}