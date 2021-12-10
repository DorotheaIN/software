package com.example.yike.model

import com.example.yike.viewModel.Activity

//data class ActivityResponse (val status: String, val result: ArrayList<Activity>)

data class ActivityResponse (val code: Int,val msg:String, val result: ArrayList<Activity>,val dataCount:Int)