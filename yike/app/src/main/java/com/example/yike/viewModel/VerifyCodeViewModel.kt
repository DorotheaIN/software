package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.SendEmailRepository


class VerifyCodeViewModel ():ViewModel(){

    private val inputCode = MutableLiveData<String>()

    //界面变量
    val inputVerifyCode = Transformations.switchMap(inputCode){
        inputCode.value?.let { it1 -> SendEmailRepository.verifyCode(it1) }
    }

    //用户方法
    fun verifyCode(code:String){
        println("code=$code")
        inputCode.value = code
    }
}