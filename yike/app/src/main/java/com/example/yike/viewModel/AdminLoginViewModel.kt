package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AdminRepository

data class AdminInfo(
    val avator:String,
    val username:String,
)

data class AdminLogin(
    val ID:String,
    val password:String,
)

class AdminLoginViewModel ():ViewModel(){

    private val adminLoginInput = MutableLiveData<AdminLogin>()

    //用户变量
    val adminLoginInfo = Transformations.switchMap(adminLoginInput){it->
        AdminRepository.adminLogin(it.ID,it.password)
    }

    //用户方法
    fun inputAdminLogin(ID: String,password: String){
        adminLoginInput.value = AdminLogin(ID, password)
    }
}