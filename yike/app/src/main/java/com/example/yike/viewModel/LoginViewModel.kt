package com.example.yike.viewModel

import androidx.lifecycle.*
import com.example.yike.service.LoginRepository


data class LoginInput(val userEmail: String, val passWord: String)
data class UserInfo(val id: String = "", val user_NAME: String = "", val status: Int = -1,
val introduction: String = "", val avator: String = "")

class LoginViewModel: ViewModel() {
    //观察对象：
    private val loginLiveData = MutableLiveData<LoginInput>()

    //界面变量：
    //需要withContext
//    private val _loginStatus = MutableLiveData<Boolean>(false)
//    val loginStatus: LiveData<Boolean> = _loginStatus

    val userInfo = Transformations.switchMap(loginLiveData) { loginInput ->
        LoginRepository.checkLoginStatus(loginInput.userEmail, loginInput.passWord)
    }

    //用户方法：
    fun checkLoginStatus(userEmail: String, passWord: String) {
        loginLiveData.value = LoginInput(userEmail, passWord)
    }

}





