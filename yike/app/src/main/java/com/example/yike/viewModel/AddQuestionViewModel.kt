package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.AddQuestionRepository

data class AddQuestionInfo(
    val content:String,
    val title:String,
    val userId:String,
)

class AddQuestionViewModel :ViewModel(){

    private val addQuestionInfo = MutableLiveData<AddQuestionInfo>()

    //界面变量
    val inputAddQuestionInfo = Transformations.switchMap(addQuestionInfo){it ->
        AddQuestionRepository.addQuestion(it.content,it.title,it.userId)
    }

    //用户方法
    fun addQues(content: String,title:String,userId: String){
        addQuestionInfo.value = AddQuestionInfo(content, title, userId)
    }
}