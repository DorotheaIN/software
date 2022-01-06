package com.example.yike.model

data class VerifyCodeResponse(
    val code:Int,
    val msr:String,
    val result:String,
    val dataCount:Int,
)