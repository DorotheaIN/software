package com.example.yike.model

import com.example.yike.viewModel.ReportInfo

data class GetReportsResponse(
    val code:Int,
    val msg:String,
    val result:ArrayList<ReportInfo>,
    val dataCount:Int
)