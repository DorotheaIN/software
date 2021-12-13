package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.GetPersonRegisterRepository
import com.example.yike.service.OfficialRepository


data class InputOfficialInfo(
    val avator:String,
    val certification:String,
    val introduction:String,
    val password:String,
    val userName:String,
)

class OfficialRegisterViewModel :ViewModel(){
    private val inputOfficialInfo = MutableLiveData<InputOfficialInfo>()

    val officialRegisterInfo = Transformations.switchMap(inputOfficialInfo) { it ->
        OfficialRepository.officialRegister(it.avator,it.certification,it.introduction,it.password,it.userName)
    }

    //用户方法
    fun checkOfficialRegisterStatus(avator:String,certification:String,introduction:String,password:String,userName:String){
        inputOfficialInfo.value = InputOfficialInfo("http://101.132.138.14/files/user/test6.jpg","ffff",introduction,password,userName)
    }
}