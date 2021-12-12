package com.example.yike

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

class NameInputState : TextFieldState(
    validator = ::isValidName,
    errorFor = ::nameValidationError
) {
    var shouldHideName: Boolean by mutableStateOf(true)
}

private fun nameValidationError(password: String): String {
    return "Please provide a password."
}

private fun isValidName(password: String): Boolean {
    return password.isNotEmpty()
}