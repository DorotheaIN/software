package com.example.yike.component

class RequiredInputState: TextFieldState(
    validator = ::isRequiredValid,
    errorFor= ::requiredErro
)

private fun isRequiredValid(required:String):Boolean{
    return !required.isBlank()
}

private fun requiredErro(required: String):String{
    return if(required.isBlank()) {
        "此处为必填项"
    } else{
        "所填信息不符合要求"
    }
}