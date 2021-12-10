package com.example.yike.model

import com.example.yike.viewModel.UserInfo


//json:
//
//{
// status: "ok"
// result:{
//          userId: "1"
//          userName: "Tom"
//          passWord: "123"
//          userStatus: True
//          }
//}
// 删除password
//status 改为user之类的？


data class LoginResponse(val code: Int, val result: UserInfo, val msg: String, val dataCount: Int) {
//    data class Result(val userId: String, val userName: String, val userStatus: String)
}

