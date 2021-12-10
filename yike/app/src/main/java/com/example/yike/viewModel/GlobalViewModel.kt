package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object GlobalViewModel: ViewModel() {
    private val globalUserInfo: MutableLiveData<UserInfo> = MutableLiveData<UserInfo>()
    private val globalQuestionList: MutableLiveData<ArrayList<Question>> = MutableLiveData<ArrayList<Question>>()

    fun updateUserInfo(userId:String, userName: String, userStatus: Int, avatar: String, introduction: String) {
        globalUserInfo.value = UserInfo(userId, userName, userStatus, introduction, avatar)
    }

    fun updataQuestionList(questionList: ArrayList<Question>, questionTheme: ArrayList<QTheme>) {
        questionTheme.forEach {
            println(it)
            val q = Question(it.id, it.title, it.description, it.followNum, it.answerNum)
            println(q)
            questionList.add(q)
        }
        globalQuestionList.value = questionList
    }

    fun getUserInfo(): UserInfo? { //为啥用这个 因为observe有问题 不知道为何？ 可以再试试
        return globalUserInfo.value
    }

    fun getQuestion(id: String): Question? {
        var q = Question("","","",0,0)
        globalQuestionList.value?.forEach {
            if (it.id == id) q = it
        }
        return q
    }
}