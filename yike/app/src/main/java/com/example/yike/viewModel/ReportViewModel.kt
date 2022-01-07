package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ReportRepository

data class AddReportInfo(
    val aID:String,
    val qID:String,
    val rID:String,
    val reason:String,
    val wID:String
)

class ReportViewModel ():ViewModel(){

    private val addReportInfo = MutableLiveData<AddReportInfo>()

    //用户变量
    val reportInfo = Transformations.switchMap(addReportInfo){
        ReportRepository.reportUser(it.aID,it.qID,it.rID,it.reason,it.wID)
    }

    //用户方法
    fun sendReportInfo(aID:String,qID:String,rID:String,reason:String,wID:String){
        addReportInfo.value = AddReportInfo(aID, qID, rID, reason, wID)
    }
}