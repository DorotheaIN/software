package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.SendEmailRepository

object GlobalViewModel: ViewModel() {
    private val globalUserInfo: MutableLiveData<UserInfo> = MutableLiveData<UserInfo>()
    private val globalQuestionList: MutableLiveData<ArrayList<Question>> = MutableLiveData<ArrayList<Question>>()

    private val globalOrgInfo:MutableLiveData<Organization> = MutableLiveData<Organization>()
    private val globalEmail: MutableLiveData<String> = MutableLiveData<String>()
    private val globalVerifyCode: MutableLiveData<String> = MutableLiveData<String>()



    fun updateUserInfo(userId:String, userName: String, userStatus: Int, avatar: String, introduction: String) {
        globalUserInfo.value = UserInfo(userId, userName, userStatus, introduction, avatar)//反了？
    }

    fun updataQuestionList(questionList: ArrayList<Question>, questionTheme: ArrayList<QTheme>) {
        questionTheme.forEach {
            val q = Question(it.id, it.title, it.description, it.followNum, it.answerNum)
            questionList.add(q)
        }
        globalQuestionList.value = questionList
    }


    fun updateOrgInfo(organization: Organization) {
        globalOrgInfo.value = organization
    }

    fun updateEmail(email:String?) {
        globalEmail.value = email
    }

    fun updateVerifyCode(verifyCode:String?) {
        globalVerifyCode.value = verifyCode
        println(verifyCode)
    }

    fun getUserInfo(): UserInfo? { //为啥用这个 因为observe有问题 不知道为何？ 可以再试试
        return globalUserInfo.value
    }

    fun getOrgInfo():Organization?{
        return globalOrgInfo.value
    }

    fun getQuestion(id: String): Question? {
        var q = Question("","","",0,0)
        globalQuestionList.value?.forEach {
            if (it.id == id) q = it
        }
        return q
    }

    fun getEmail(): String? { //为啥用这个 因为observe有问题 不知道为何？ 可以再试试
        return globalEmail.value
    }

    fun getVerifyCode(): String? { //为啥用这个 因为observe有问题 不知道为何？ 可以再试试
        return globalVerifyCode.value
    }


    private val emailLiveData = MutableLiveData<EmailInput>()

    //界面变量
    val sendEmailInfo = Transformations.switchMap(emailLiveData) { emailInput ->
        SendEmailRepository.sendEmail(emailInput.email)
    }


    //用户方法
    fun checksendStatus(inputEmail: String) {
        emailLiveData.value = EmailInput(inputEmail)
    }
}