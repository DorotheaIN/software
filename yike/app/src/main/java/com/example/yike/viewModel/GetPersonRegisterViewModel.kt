package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.GetPersonRegisterRepository
import com.example.yike.service.SendEmailRepository

data class InputRegisterInfo(val email:String,val name:String,val password:String)

class GetPersonRegisterViewModel : ViewModel(){

    private val inputRegisterInfos = MutableLiveData<InputRegisterInfo>()

    //界面变量
    val personRegisterInfo = Transformations.switchMap(inputRegisterInfos) { inputRegisterInfo ->
        GetPersonRegisterRepository.getPersonRegister(inputRegisterInfo.email,inputRegisterInfo.name,inputRegisterInfo.password)
    }
    //用户方法
    fun checkPersonRegisterStatus(email:String,name:String,password: String) {
        println(email)
        println(name)
        println(password)
        inputRegisterInfos.value = InputRegisterInfo(email,name,password)
    }

}