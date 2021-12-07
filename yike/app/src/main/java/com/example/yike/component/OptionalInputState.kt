package com.example.yike.component

class OptionalInputState: TextFieldState(
    validator = ::isValid,
    errorFor = ::isError
)

private fun isValid(text: String):Boolean{
    return true
}

private fun isError(text: String):String{
    return ""
}