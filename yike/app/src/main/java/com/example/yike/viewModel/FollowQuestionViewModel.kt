package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.FollowQuestionRepository
import com.example.yike.service.PublishQuestionRepository

//data class FollowQuestion(
//    val title:String,
//    val content:String,
//    val id:Int,
//    val individualUserID:String
//)

data class FollowQuestion(
    val individualUserID:String?,
    val question_ID:String?,
    val title:String?,
    val content:String?,
    val user_NAME:String?,
    val avator:String?,
    val introduction:String?,
)

class FollowQuestionViewModel(
): ViewModel() {
    //观察对象：
    private val id = MutableLiveData<String>()
    private val isInit = MutableLiveData<Boolean>(false)

    //界面变量
    val followQuestionList = Transformations.switchMap(id) { it->
        FollowQuestionRepository.getFollowQuestion(it)
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