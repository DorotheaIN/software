package com.example.yike

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

class PasswordInputState : TextFieldState(
    validator = ::isValidPassword,
    errorFor = ::passwordValidationError
) {
    var shouldHidePassword: Boolean by mutableStateOf(true)
}

private fun passwordValidationError(password: String): String {
    return "请输入密码"
}

private fun isValidPassword(password: String): Boolean {
    return password.isNotEmpty()
}