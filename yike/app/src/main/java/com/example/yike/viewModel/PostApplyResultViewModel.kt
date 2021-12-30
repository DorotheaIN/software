package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AdminRepository

data class PostInfo(
    val content:String,
    val title:String,
    val to:String
)

class PostApplyResultViewModel ():ViewModel(){

    private val postInfo = MutableLiveData<PostInfo>()

    //界面变量
    val sendPostInfo = Transformations.switchMap(postInfo){
        AdminRepository.postApplyResult(it.content,it.title,it.to)
    }

    //用户方法
    fun PostApplyResult(content: String,title: String,to: String){
        println(to)
        postInfo.value= PostInfo(content, title, to)
    }
}