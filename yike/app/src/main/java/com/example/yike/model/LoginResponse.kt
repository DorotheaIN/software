package com.example.yike.model


//json:
//{
// status: "ok"
// result:{
//          userId: "1"
//          userName: "Tom"
//          passWord: "123"
//          userStatus: True
//          }
//}
data class LoginResponse(val status: String, val result: Result) {
    data class Result(val userId: String, val userName: String, val passWord: String, val userStatus: Boolean)
}
//也可以这样定义：
//data class LoginResponse(val status: String, val result: UserInfo)
//userinfo 在viewmodel中定义
