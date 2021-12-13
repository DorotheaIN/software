package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.CommentAnswerRepository

data class InputCommentInfo(
    val answerId:String,
    val content:String,
    val userId:String,
)

class CommentViewModel ():ViewModel(){

    private val commentInfo = MutableLiveData<InputCommentInfo>()

    val inputComment = Transformations.switchMap(commentInfo){
        CommentAnswerRepository.comment(it.answerId,it.content,it.userId)
    }

    //用户方法
    fun checkInputComment(answerId: String,content:String,userId: String){
        commentInfo.value = InputCommentInfo(answerId,content,userId)
    }
}