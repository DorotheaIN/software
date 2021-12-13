package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.LoginRepository
import com.example.yike.service.SendEmailRepository


data class EmailInput(val email:String)
//data class SendEmailInfo(val result: String)


class SendEmailViewModel :ViewModel(){



    private val emailLiveData = MutableLiveData<EmailInput>()

    //界面变量
    val sendEmailInfo = Transformations.switchMap(emailLiveData) { emailInput ->
        SendEmailRepository.sendEmail(emailInput.email)
    }


    //用户方法
    fun checksendStatus(inputEmail: String) {
        emailLiveData.value = EmailInput(inputEmail)
    }

}