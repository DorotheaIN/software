package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.SendEmailRepository

class SimpleVerifyViewModel ():ViewModel(){

    private val to = MutableLiveData<String>()

    val getTo = Transformations.switchMap(to){to->
        SendEmailRepository.simpleVerify(to)
    }

    fun sendEmail(email:String){
        to.value = email
    }
}