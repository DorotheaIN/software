package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AdminRepository
import com.example.yike.service.FollowQuestionRepository

data class ApplyInfo(
    val id:Int,
    var status: Int,
    val username: String,
    val introduction:String,
    val avator:String,
    val certification:String,
    val email:String
)

class AdminApplyViewModel ():ViewModel(){

    private val isGet = MutableLiveData<Boolean>(false)

    //界面变量
    val applicationInfoList = Transformations.switchMap(isGet){
        AdminRepository.getApplications()
    }

    //用户方法
    fun init(){
        isGet.value = !isGet.value!!
    }

    fun changeInfoStatus(ID:Int,status:Int){
        for(applicationInfo in applicationInfoList.value!!){
            println(ID)
            println(status)
            if(applicationInfo.id == ID){
                applicationInfo.status = status
                isGet.value = !isGet.value!!
            }
        }
    }
}