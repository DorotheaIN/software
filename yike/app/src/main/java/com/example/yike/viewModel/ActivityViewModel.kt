package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository



//data class Organization(
//    val username:String,
//    val avator:String,
//    val introduction:String
//)

data class Organization(
    val id: Int,
    val status:Int,
    val introduction:String,
    val avator:String,
    val username:String,
)

//data class Activity(
//    val id:Int,
//    val title:String,
//    val organizer: Organization,
//    val img:String
//)

data class Activity(
    val title:String,
    val img:String,
    val date:String,
    val place:String,
    val form:String,
    val introduction:String,
    val content:String,
    val genres:String,
    var likeNum:Int,
    val capacity:Int,
    var status:Int,
    var subscriberNum:Int,/////////////
    val organizer: Organization,
    val id:Int,
)



class ActivityViewModel(): ViewModel() {
    //观察对象：
    private val isInit = MutableLiveData<Boolean>(false)

    //界面变量
    val activityList = Transformations.switchMap(isInit) {
        ActivityRepository.getActivityList()
    }
    //用户方法：
    fun init() {
        isInit.value = true
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

