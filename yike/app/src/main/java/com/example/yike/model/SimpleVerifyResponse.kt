package com.example.yike.model

data class SimpleVerifyResponse(
    val code:Int,
    val msg:String,
    val result:String,
    val dataCount:Int
)