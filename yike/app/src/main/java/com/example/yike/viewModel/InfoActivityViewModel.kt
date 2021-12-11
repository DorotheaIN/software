package com.example.yike.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.data.Organization
import com.example.yike.service.ActivityRepository

data class InfoEvaluation(
    val evaluaterName:String,
    val description:String,
    val score:Int
)

data class InfoActivity(
    val id:Int,
    val title:String,
    val organizer: Organization,
    val img:Int,
    val date:String,
    val place:String,
    val form:String,
    val capacity:Int,
    val introduction:String,
    val content:String,
    val genres:List<String>,
    var status:Int,
    var likeNum:Int,
    var subscriberNum:Int,
)


class InfoActivityViewModel(): ViewModel() {
    //观察对象：
    private val isInit = MutableLiveData<Boolean>(false)

    //界面变量
    val infoActivityList = Transformations.switchMap(isInit) {
        ActivityRepository.getActivityList()
    }
    //用户方法：
    fun init() {
        isInit.value = true
    }
}

