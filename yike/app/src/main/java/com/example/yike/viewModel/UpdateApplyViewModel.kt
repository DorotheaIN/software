package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AdminRepository

data class ApplyStatus(
    val ID:String,
    val flag:String,
)

class UpdateApplyViewModel ():ViewModel(){

    private val applyStatus = MutableLiveData<ApplyStatus>()

    //界面变量
    val changeApplyStatus = Transformations.switchMap(applyStatus){ applyStatus ->
        AdminRepository.updateOUserStatus(applyStatus.ID,applyStatus.flag)
    }

    //用户方法
    fun updateApplyStatus(ID: String,status:String){
        applyStatus.value = ApplyStatus(ID,status)
    }

}