package com.example.yike

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

class VerifyCodeInputState : TextFieldState(
    validator = ::isValidVerifyCode,
    errorFor = ::verifyCodeValidationError
) {
    var shouldHideName: Boolean by mutableStateOf(true)
}

private fun verifyCodeValidationError(password: String): String {
    return "Please provide a password."
}

private fun isValidVerifyCode(password: String): Boolean {
    return password.isNotEmpty()
}