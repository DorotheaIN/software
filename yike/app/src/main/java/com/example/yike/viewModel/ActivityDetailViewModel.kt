package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.UserActivityRepository
import com.example.yike.viewModel.GlobalViewModel.getUserInfo

//data class Evaluation(
//    val activityID:Int,
//    val reviewerID:Int,
//    val reviewerName:String,
//    val reviewerAvator:String,
//    val content:String,
//    val score:Int
//)

data class Evaluation(
    val reviewerID:Int,
    val activityID:Int,
    val content:String,
    val score:Int,
    val reviewerName:String,
    val reviewerIntroduction:String,
    val reviewerAvator:String
)

//data class ActivityDetail(
//    val id:Int,
//    val title:String,
//    val organizer: Organization,
//    val img:String,
//    val date:String,
//    val place:String,
//    val form:String,
//    val capacity:Int,
//    val introduction:String,
//    val content:String,
//    val genres:String,
//    var status:Int,
//    var likeNum:Int,
//    var subscriberNum:Int,
//)

data class LikeRelation(
    val activityID: Int,
    val userID:String
)

data class ActivityDetail(
    val title:String,
    val img:String,
    val date:String,
    val place:String,
    val form:String,
    val introduction:String,
    val content:String,
    val genres:String,
    var likeNum:Int,
    val capacity:Int,
    var status:Int,
    var subscriberNum:Int,/////////////
    val organizer: Organization,
    val id:Int,
)

data class ActivityID(val activityID: Int)

class ActivityDetailViewModel :ViewModel(){
    //观察对象：
//    private val activityID = MutableLiveData<ActivityID>()
    private val id = MutableLiveData<Int>()


    val activityDetail = Transformations.switchMap(id){it->
        ActivityRepository.getActivityDetail(it)
    }

    val evaluationList = Transformations.switchMap(id){it->
        ActivityRepository.getEvaluationList(it)
    }

    private val userID = MutableLiveData<String>()

    val activityRecommendedList = Transformations.switchMap(userID){it->
        ActivityRepository.getActivityRecommended(it)
    }



    fun save(likeStatus:Boolean,subscribeStatus:Boolean){
        if(likeStatus){
            val userInfo = getUserInfo()
            if(userInfo != null){
                activityDetail.value?.let { UserActivityRepository.postLikeActivity(it.id,userInfo.userId) }
            }
        }
    }

    fun init(ID:Int){
        id.value = ID
        val userInfo = getUserInfo()
        if(userInfo != null){
            userID.value = userInfo.userId
        }
    }

}