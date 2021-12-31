package com.example.yike.model

import com.example.yike.viewModel.AdminInfo

data class AdminLoginResponse(
    val code:Int,
    val msg:String,
    val result: AdminInfo,
    val dataCount:Int
)

