package com.example.yike.model

import java.util.concurrent.CountDownLatch

data class UpdateIUserStatusResponse(
    val code :Int,
    val msg:String,
    val result:Int,
    val dataCount:Int
)