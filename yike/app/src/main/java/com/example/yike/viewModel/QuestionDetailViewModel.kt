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
    _questionId: String
): ViewModel() {
    private val questionBody = GlobalViewModel.getQuestion(_questionId)
    private val userId = GlobalViewModel.getUserInfo()?.id
    val questionId = _questionId
    private val _isGet = MutableLiveData<Boolean>()
    val isGet: LiveData<Boolean> = _isGet
    private val isPost = MutableLiveData<Boolean>()


    val answerList = Transformations.switchMap(_isGet) {
        AnswerRepository.getAnswerList(_questionId)
    }

    val originStatus = Transformations.switchMap(_isGet) {
        QuestionRepository.checkQuestionStatus(_questionId, userId!!)
    }

    val postResult = Transformations.switchMap(isPost) {
        println("!!!!!!!!!!!!!!!!!!")
        QuestionRepository.postQuestionStatus(userId = userId!!, questionId = questionId)
    }

    //-1 没请求：
    //1 已收藏
    //0 没收藏
    private val _questionStatus: MutableLiveData<Int> = MutableLiveData<Int>(-1)
    val questionStatus: LiveData<Int> = _questionStatus

    fun isSet(): Boolean {
        return _questionStatus.value != -1
    }

    fun setQuestionStatus() {
        _questionStatus.value = originStatus.value
    }

    fun convertQuestionStatus() {
        val v = _questionStatus.value
        if(v == 1) _questionStatus.value = 0
        else _questionStatus.value = 1
    }


    fun postQuestionStatus() {
        if (_questionStatus.value != originStatus.value) {
            isPost.value = true
        }
    }

    fun getAnswerList() {
        _isGet.value = true
    }

    fun getQuestionBody(): Question? {
        return questionBody
    }


}