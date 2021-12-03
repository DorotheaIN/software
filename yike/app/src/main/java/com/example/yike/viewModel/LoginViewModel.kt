package com.example.yike.viewModel

import androidx.lifecycle.*
import com.example.yike.service.LoginRepository


data class LoginInput(val userName: String, val passWord: String)
data class UserInfo(val userId: String, val userName: String, val passWord: String, val userStatus: Boolean)

class LoginViewModel: ViewModel() {
    //观察对象：
    private val loginLiveData = MutableLiveData<LoginInput>()

    //界面变量：
    //需要withContext
//    private val _loginStatus = MutableLiveData<Boolean>(false)
//    val loginStatus: LiveData<Boolean> = _loginStatus

    val userInfo = Transformations.switchMap(loginLiveData) { loginInput ->
        LoginRepository.checkLoginStatus(loginInput.userName, loginInput.passWord)
    }

    //用户方法：
    fun checkLoginStatus(userName: String, passWord: String) {
        loginLiveData.value = LoginInput(userName, passWord)
    }
}





