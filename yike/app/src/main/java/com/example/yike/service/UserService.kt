package com.example.yike.service

import com.example.yike.model.LoginResponse
import com.example.yike.model.QuestionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("login")//path看swagger
    fun getLoginStatus(@Query("u") user: String, @Query("p") passWord: String): Call<LoginResponse>

    @GET("discuss")//path看swagger
    fun getQuestionList(): Call<QuestionResponse>
}