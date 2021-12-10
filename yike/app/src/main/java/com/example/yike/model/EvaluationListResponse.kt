package com.example.yike.model

import com.example.yike.viewModel.Evaluation


//data class EvaluationListResponse(val status: String, val result: ArrayList<Evaluation>)

data class EvaluationListResponse(val code: Int,val msg:String, val result: ArrayList<Evaluation>,val dataCount:Int)