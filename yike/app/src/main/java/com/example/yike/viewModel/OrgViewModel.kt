package com.example.yike.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.OrgLoginRepository
import com.example.yike.service.OrganizationRepository

class OrganizationViewModel(): ViewModel() {

    private val _isGet = MutableLiveData<Boolean>()
    val isGet: LiveData<Boolean> = _isGet

    //之后要改成从Global获取
    val organizationInfo = Transformations.switchMap(_isGet) {
        OrgLoginRepository.checkLoginStatus(3,"tongji_sse")
    }

    val activityList = Transformations.switchMap(_isGet){
        OrganizationRepository.getActivityByOrganization(3)
    }

    fun getInfo(){
        _isGet.value = true
    }

}
