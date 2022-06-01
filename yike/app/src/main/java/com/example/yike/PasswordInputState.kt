package com.example.yike

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import java.util.regex.Pattern

private const val regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$"

class PasswordInputState : TextFieldState(
    validator = ::isValidPassword,
    errorFor = ::passwordValidationError
) {
    var shouldHidePassword: Boolean by mutableStateOf(true)
}

private fun passwordValidationError(password: String): String {
    return if (password.isBlank()){
        "密码不能为空"
    }else{
        "密码格式不正确,需包含字母且长度为8-20位"
    }
}

private fun isValidPassword(password: String): Boolean {
      return Pattern.matches(regex,password)
//    return password.isNotEmpty()
}