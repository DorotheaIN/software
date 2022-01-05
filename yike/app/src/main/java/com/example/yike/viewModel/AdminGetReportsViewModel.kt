package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AdminRepository

data class ReportInfo(
    val isReportedID:String,
    val reason:String,
    val question_ID:String,
    val answer_ID:String,
    val whistleblowerID:String
)

class AdminGetReportsViewModel ():ViewModel(){

    private val isGet = MutableLiveData<Boolean>(false)

    //界面变量
    val reportsList = Transformations.switchMap(isGet){
        AdminRepository.getReports()
    }

    //用户方法
    fun init(){
        isGet.value = !isGet.value!!
    }

}