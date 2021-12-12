package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.GetAllCommentByQuestionIdAndAnswerIdRepository
import com.example.yike.service.PublishQuestionRepository

data class QuesAnswer(
    val answer:String,
    val question:String,
    val time:String,
    val info:AnswererInfo,
    val comment:ArrayList<CommentInfo>,
)

data class AnswererInfo(
    val id :String,
    val name:String,
    val pic:String,
    val intro:String,
)

data class CommentInfo(
    val info:CommenterInfo,
    val content:String,
)

data class CommenterInfo(
    val id:String,
    val name:String,
    val pic: String,
    val intro: String,
)

data class AnswerQuesId(
    val answerId: String,
    val quesId:String,
)

class DetailedAnswerViewModel (
    _questionId:String,
    _answerId:String,
        ):ViewModel(){
    private val quesAnswerInfo = MutableLiveData<QuesAnswer>()
    private val answerQuesId=MutableLiveData<AnswerQuesId>()
    val questionId = _questionId
    val answerId = _answerId

    //界面变量
    val quesAnswerInfoList = Transformations.switchMap(answerQuesId) { answerQuesId->
        GetAllCommentByQuestionIdAndAnswerIdRepository.getAllCommentByQuestionIdAndAnswerId(answerQuesId.answerId,answerQuesId.quesId)
    }

    //用户方法
    fun slectQuesAnswer(answerId: String,quesId: String){
        answerQuesId.value = AnswerQuesId(answerId,quesId)
    }
}