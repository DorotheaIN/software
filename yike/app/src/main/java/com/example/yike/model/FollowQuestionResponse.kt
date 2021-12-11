package com.example.yike.model

import com.example.yike.viewModel.FollowQuestion
import com.example.yike.viewModel.PublishQuestion

data class FollowQuestionResponse (val code:Int, val msg:String, val result:ArrayList<FollowQuestion>, val dataCount:Int)