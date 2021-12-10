package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository

class ActivityCorrectViewModel() : ViewModel(
){
    //观察对象：
    private val isInit = MutableLiveData<Boolean>(false)

    private val activityID = MutableLiveData<Int>()

    //界面变量
    val activityInfo = Transformations.switchMap(activityID) {it->
        ActivityRepository.getActivityDetail(it)
    }

    //用户方法：
    fun init(id:Int) {
        isInit.value = true
        activityID.value = id
    }
}