package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.PublishQuestionRepository


data class PublishQuestion(
    val title:String,
    val content:String,
    val id:Int,
    val individualUserID:String
)


//class DetailedAnswerViewModel: ViewModel() {
//    //观察对象：
//    private val detailedAnswerData = MutableLiveData<DetailedAnswer>()
//
//    //界面变量：
//    //需要withContext
////    private val _loginStatus = MutableLiveData<Boolean>(false)
////    val loginStatus: LiveData<Boolean> = _loginStatus
//
//    val userInfo = Transformations.switchMap(loginLiveData) { loginInput ->
//        LoginRepository.checkLoginStatus(loginInput.userName, loginInput.passWord)
//    }
//
//    //用户方法：
//    fun checkLoginStatus(userName: String, passWord: String) {
//        loginLiveData.value = LoginInput(userName, passWord)
//    }
//
//}

class PublishQuestionViewModel(
): ViewModel() {
    //观察对象：
    private val id = MutableLiveData<String>()
    private val isInit = MutableLiveData<Boolean>(false)

    //界面变量
    val publishQuestionList = Transformations.switchMap(id) { it->
        PublishQuestionRepository.getPublishQuestion(it)
    }
    //用户方法：
    fun init() {
        id.value = GlobalViewModel.getUserInfo()?.id
    }
//
//    private val like = Transformations.switchMap(isInit) {
//        Activity
//    }
//
//    //查询是否建立点赞关系  起始和结束api
//    private val like = MutableLiveData<Boolean>(false)
//
//    fun clicklikeButton(){
//        like.value = !like.value!!
//    }


}
