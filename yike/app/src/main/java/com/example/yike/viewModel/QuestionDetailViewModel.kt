package com.example.yike.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AnswerRepository
import com.example.yike.service.QuestionRepository

data class Answer(val id: String, val content: String,
                  val approveNum: Int, val disapproveNum: Int, val userInfo: TinyUserInfo) {
    data class TinyUserInfo(val name: String, val avatar: String)
}


class QuestionViewModel(
    questionId: String
): ViewModel() {
    private val questionBody = GlobalViewModel.getQuestion(questionId)
    private val _isGet = MutableLiveData<Boolean>()
    val isGet: LiveData<Boolean> = _isGet

    val answerList = Transformations.switchMap(_isGet) {
        AnswerRepository.getAnswerList()
    }

    fun getAnswerList() {
        _isGet.value = true
    }

    fun getQuestionBody(): Question? {
        return questionBody
    }

    fun followQuestion(userId: String) {

    }


}