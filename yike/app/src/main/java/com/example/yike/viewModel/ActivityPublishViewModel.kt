package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.OrgLoginRepository
import com.example.yike.service.OrganizationRepository

class ActivityPublishViewModel() : ViewModel(
){
    //观察对象：
    private val isInit = MutableLiveData<Boolean>(false)

    private val activityToPub = MutableLiveData<Activity>()

//    private val activityID = MutableLiveData<Int>()
//
//    //界面变量
//    val activityInfo = Transformations.switchMap(activityID) { it->
//        ActivityRepository.getActivityDetail(it)
//    }
//
    //用户方法：
    fun init() {
        isInit.value = true
    }
    val organizationInfo = Transformations.switchMap(isInit) {
        OrgLoginRepository.checkLoginStatus(3,"tongji_sse")
    }

    val publishRes = Transformations.switchMap(activityToPub){it->
        OrganizationRepository.publishActivity(it.capacity,it.content,it.date,it.form,it.genres,it.img,it.introduction,it.organizer.id,it.place,it.status,it.title)
    }

    fun publish(activity: Activity){
        activityToPub.value = activity
    }
}