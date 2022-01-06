package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AdminRepository

data class UpdateIUserInfo(
    val ID :String,
    val flag:String,
)

class AdminUpdateIUserViewModel ():ViewModel(){

    private val updateIUserInfo = MutableLiveData<UpdateIUserInfo>()

    //界面变量
    val changeIUserStatus = Transformations.switchMap(updateIUserInfo){
        AdminRepository.updateIUserStatus(it.ID,it.flag)
    }

    //用户方法
    fun updateIUserStatus(ID: String,status:String){
        updateIUserInfo.value=UpdateIUserInfo(ID,status)
    }
}