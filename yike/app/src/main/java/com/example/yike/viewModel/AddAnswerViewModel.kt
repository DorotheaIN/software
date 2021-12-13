package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.CommentAnswerRepository
import com.example.yike.service.FollowQuestionRepository


data class InputAnswer(
    val content:String,
    val questionId:String,
    val userId:String,
)

class AddAnswerViewModel (
    _questionId:String,
    _questionTitle:String,
//    _answerId:String,
        ):ViewModel(){

    private val inputAnswer = MutableLiveData<InputAnswer>()

    val questionId = _questionId
    val questionTitle = _questionTitle
//    val answerId = _answerId

    //界面变量
    val inputAnswerInfo = Transformations.switchMap(inputAnswer) { it ->
        CommentAnswerRepository.addAnswer(it.content, it.questionId, it.userId)
    }

    //用户方法
    fun checkInputAnswer(content:String,questionId: String,userId: String){
        inputAnswer.value = InputAnswer(content,questionId,userId)
    }
}