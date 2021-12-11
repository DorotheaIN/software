package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.OrgLoginRepository
import com.example.yike.service.OrganizationRepository

class OrganizationViewModel(): ViewModel() {
    //观察对象：
    private val isInit = MutableLiveData<Boolean>(false)

    private val id = MutableLiveData<Int>()

    //界面变量
    val organizationInfo = Transformations.switchMap(isInit) {
        OrgLoginRepository.checkLoginStatus(3,"tongji_sse")
    }

    val activityList = Transformations.switchMap(isInit){
        OrganizationRepository.getActivityByOrganization(3)
    }
    //用户方法：
    fun init() {
        isInit.value = true
        id.value = 6
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
