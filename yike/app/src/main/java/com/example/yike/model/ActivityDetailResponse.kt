package com.example.yike.model

import com.example.yike.viewModel.ActivityDetail


//data class ActivityDetailResponse(val status: String, val result: ActivityDetail)

data class ActivityDetailResponse(val code: Int,val msg:String, val result: ActivityDetail,val dataCount:Int)

