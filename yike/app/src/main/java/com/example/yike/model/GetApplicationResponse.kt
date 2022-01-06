package com.example.yike.model

import com.example.yike.viewModel.ApplyInfo

data class GetApplicationResponse(
    val code: Int,
    val msg:String,
    val result:ArrayList<ApplyInfo>,
    val dataCount:Int,
)