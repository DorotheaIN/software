package com.example.yike.service

import com.example.yike.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("login")
    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>


    @GET("discuss")
    fun getQuestionList(): Call<QuestionResponse>

    @GET("recommenddis")
    fun getQuestionByTheme(): Call<QThemeResponse>

    @GET("answer")
    fun getAnswerList(): Call<AnswerResponse>

    @GET("/test/doLogin")
    fun getTest(): Call<TestResponse>
}