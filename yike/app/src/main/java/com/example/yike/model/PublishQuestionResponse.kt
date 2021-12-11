package com.example.yike.model

import com.example.yike.viewModel.PublishQuestion


data class PublishQuestionResponse (val code:Int, val msg:String, val result:ArrayList<PublishQuestion>, val dataCount:Int)