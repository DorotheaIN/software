package com.example.yike.model

import com.example.yike.viewModel.CommentInfo
import com.example.yike.viewModel.QuesAnswer

//data class GetAllCommentByQuestionIdAndAnswerIdResponse (
//    val code:Int,
//    val msg:String,
//    val result:ArrayList<QuesAnswer>,
//    val dataCount:Int,
//    )
data class GetAllCommentByQuestionIdAndAnswerIdResponse (
    val code:Int,
    val msg:String,
    val result:QuesAnswer,
    val dataCount:Int,
)